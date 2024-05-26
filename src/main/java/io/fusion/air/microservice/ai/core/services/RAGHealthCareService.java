
package io.fusion.air.microservice.ai.core.services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import dev.langchain4j.service.AiServices;
import io.fusion.air.microservice.ai.core.assistants.Assistant;
import io.fusion.air.microservice.ai.core.assistants.HealthCareAssistant;
import io.fusion.air.microservice.ai.core.assistants.PatientDataExtractorAssistant;
import io.fusion.air.microservice.ai.core.models.Patient;
import io.fusion.air.microservice.ai.core.models.PatientRequest;
import io.fusion.air.microservice.ai.core.prompts.StructuredPromptDiagnosisDetails;
import io.fusion.air.microservice.ai.core.prompts.StructuredPromptDiagnosisSummary;
import io.fusion.air.microservice.ai.core.prompts.StructuredPromptPatientId;
import io.fusion.air.microservice.ai.core.prompts.StructuredPromptPatientName;
import io.fusion.air.microservice.ai.utils.AiBeans;
import io.fusion.air.microservice.ai.utils.AiConstants;
import org.springframework.stereotype.Service;

/**
 * RAG HealthCare Service
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Service
public class RAGHealthCareService implements HealthCareAssistant {

    private final ChatLanguageModel model;
    private final HealthCareAssistant assistant;

    /**
     * Create RAG Health Care Service
     */
    public RAGHealthCareService() {
        this(AiConstants.LLM_OPENAI, AiConstants.GPT_4o);
    }

    /**
     * Create RAG HealthCare Service
     * @param _groupName
     * @param _modelName
     */
    public RAGHealthCareService(String _groupName, String _modelName) {
        model = createLanguageModel( _groupName,  _modelName);
        assistant = RAGHealthCareBuilder.createHealthCareAssistant( model);
    }

    /**
     * Analyze the User Query and Return the response
     * @param _userQuery
     * @return
     */
    public String chat(String _userQuery) {
        if(_userQuery == null) {
            return "Invalid Input!";
        }
        PatientRequest request = analyzeTheQuery(_userQuery);
        if(request.isValidData()) {
            String patientRef = (request.getPatientName() != null) ? request.getPatientName() : request.getPatientId();
            return assistant.chat(patientRef, request.getUserQuery());
        }
        return assistant.chat(request.getUserQuery());
    }

    /**
     * Chat Memory with a Memory ID
     *
     * @param _patient
     * @param _userMessage
     * @return
     */
    @Override
    public String chat(String _patient, String _userMessage) {
        return assistant.chat(_patient, _userMessage);
    }

    /**
     * Return the User Query
     * @param _userQuery
     * @return
     */
    private PatientRequest analyzeTheQuery(String _userQuery) {
        String patientName = null, patientId = null, userQuery = null;
        // Extract Patient Name
        Patient patient = patientNameExtractor(_userQuery);
        if(patient.isValid()) {
            patientName = patient.toString();
            userQuery = getPatientPrompt(patient).text();
            System.out.println(">> Patient:"+patientName+" << "+userQuery);
            // return  getPatientPrompt(patient).text();
        } else {
            // Extract Patient ID
            long patientLongId = patientIdExtractor(_userQuery);
            if (patientLongId > 0) {
                patientId = "" + patientLongId;
                userQuery = getPatientIdPrompt(patientLongId).text();
                System.out.println(">> Patient:"+patientId+" << "+userQuery);
                // return  getPatientIdPrompt(patientId ).text();
            }
        }
        // If UserQuery is still NULL then check for special commands starting with [P:
        if(userQuery == null) {
            userQuery = getDiagnosisQuery(_userQuery);
        }
        return new PatientRequest(patientName, patientId, userQuery) ;
    }

    /**
     * Get the Patient
     * @param _request
     * @return
     */
    private Patient patientNameExtractor(String _request) {
        // Create Chat Language Model - Open AI GPT 3.5 Turbo
        ChatLanguageModel model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_3_5_TURBO);
        PatientDataExtractorAssistant extractor = AiServices.create(PatientDataExtractorAssistant.class, model);
        Patient patient = extractor.extractPatientNameFrom(_request);
        if(patient.isValid()) {
            AiBeans.printResult(_request, patient.toString());
        }
        return patient;
    }

    /**
     * Extract Patient ID
     * @param _request
     * @return
     */
    private long patientIdExtractor(String _request) {
        PatientDataExtractorAssistant extractor = AiServices.create(PatientDataExtractorAssistant.class, model);
        long patientId = -1;
        try { patientId = extractor.extractPatientId(_request); } catch (NumberFormatException e) {}
        if(patientId > 0) {
            AiBeans.printResult(_request, "" + patientId);
        }
        return patientId;
    }

    /**
     * Return Patient Prompt
     * @param _patient
     * @return
     */
    private Prompt getPatientPrompt(Patient _patient) {
        return StructuredPromptProcessor.toPrompt(new StructuredPromptPatientName(_patient.toString()));
    }

    /**
     * Return the Patient Id Prompt
     * @param _patientId
     * @return
     */
    private Prompt getPatientIdPrompt(long _patientId) {
        return StructuredPromptProcessor.toPrompt(new StructuredPromptPatientId(_patientId));
    }

    /**
     * Return User Query
     * @param _userQuery
     * @return
     */
    private String getDiagnosisQuery(String _userQuery) {
        if (_userQuery.startsWith("[P: ") || _userQuery.startsWith("[p: ")) {
            String[] input = _userQuery.split(":");
            // Input Array
            String[] ia = input[1].split(",");
            if (ia.length == 2) {
                // Structured Prompt
                StructuredPromptDiagnosisDetails details = new StructuredPromptDiagnosisDetails(ia[0], ia[1]);
                // Created Prompt
                Prompt prompt = StructuredPromptProcessor.toPrompt(details);
                _userQuery = prompt.text();
            } else {
                // Structured Prompt
                StructuredPromptDiagnosisSummary summary = new StructuredPromptDiagnosisSummary(ia[0]);
                // Created Prompt
                Prompt prompt = StructuredPromptProcessor.toPrompt(summary);
                _userQuery = prompt.text();
            }
        }
        return _userQuery;
    }

    /**
     * Return Chat Language Model
     * @param _groupName
     * @param _modelName
     * @return
     */
    private  ChatLanguageModel createLanguageModel(String _groupName, String _modelName) {
        ChatLanguageModel model = null;
        // If Not Valid Go with the default Model OpenAI - GPT 3.5 Turbo
        if(!isValidModel(_groupName ,_modelName)) {
            model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_3_5_TURBO);
            AiBeans.printModelDetails(_groupName, AiConstants.GPT_3_5_TURBO);
        } else {
            // IF Valid and If Open AI then go with GPT 3.5 Turbo
            if(_groupName.equalsIgnoreCase(AiConstants.LLM_OPENAI)) {
                model = AiBeans.getChatLanguageModelOpenAi(AiConstants.GPT_3_5_TURBO);
                AiBeans.printModelDetails(_groupName, AiConstants.GPT_3_5_TURBO);
            } else {
                // Else Any other
                model = getChatLanguageModel( _groupName,  _modelName);
                AiBeans.printModelDetails(_groupName, _modelName);
            }
        }
        return model;
    }

    /**
     * Returns ChatLanguageModel based on the LLM Group
     * @param _groupName
     * @param _modelName
     * @return
     */
    private ChatLanguageModel getChatLanguageModel(String _groupName, String _modelName) {
        switch(_groupName) {
            case AiConstants.LLM_ANTHROPIC: return AiBeans.getChatLanguageModelAnthropic(_modelName);
            case AiConstants.LLM_OLLAMA: return AiBeans.getChatLanguageModelLlama(_modelName);
            case AiConstants.LLM_VERTEX: return AiBeans.getChatLanguageModelGoogle(_modelName);
        }
        return AiBeans.getChatLanguageModelOpenAi(_modelName);
    }

    /**
     * Checks if the Group and Model Name are valid
     * @param _groupName
     * @param _modelName
     * @return
     */
    private boolean isValidModel(String _groupName, String _modelName) {
        if(_groupName == null) { return false; }
        if(_modelName == null) { return false; }
        return true;
    }
}
