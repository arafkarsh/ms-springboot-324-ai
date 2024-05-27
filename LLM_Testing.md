# Gen Ai - Springboot / Langchain4J Examples

## Generative AI Examples: Comparison of 7 LLMs (2 remote & 5 Local)

| #   | Example              | GPT 4o             | Meta Llama3     | Mistral          | Microsoft Phi-3  | Google Gemma     | TII Falcon 2    | Claude 3       | Details                                                                  |
|-----|----------------------|--------------------|-----------------|------------------|------------------|------------------|-----------------|----------------|--------------------------------------------------------------------------|
| 1.  | Hello World          | :green_circle:     | :green_circle:  | :green_circle:   | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | First Hello World Generative AI Example                                  |
| 2.  | Complex World        | :green_circle:     | :green_circle:  | :red_circle: M1  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | Complex Example with Word and Math Problems                              |
| 3.  | Custom Data          | :green_circle:     | :green_circle:  | :green_circle:   | :green_circle:   | :green_circle:   | :red_circle: F1 | :green_circle: | Use your data (docs) for Search / Query                                  |
| 4.  | Image Generation     | :green_circle:     | :red_circle: L1 | :red_circle: M2  | :red_circle: P1  | :orange_circle:  | :red_circle: F2 | :yellow_circle:| Create an Image using text prompt                                        | 
| 5.  | Prompt Template      | :green_circle:     | :green_circle:  | :red_circle: M3  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | Get Structured Response using Structured Prompt                          |
| 6.  | Tools Annotation     | :green_circle:     | :red_circle: L2 | :red_circle: M4  | :red_circle: P2  | :red_circle: G1  | :red_circle: F3 | :green_circle: | Use Tools annotation for Custom Search / Queries                         |
| 7.  | Chat Memory          | :green_circle:     | :green_circle:  | :green_circle:   | :red_circle: P3  | :red_circle: G2  | :green_circle:  | :green_circle: | How to create a ChatBot in a Conversational Context                      |
| 8.  | FewShot              | :green_circle:     | :green_circle:  | :red_circle: M5  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | Create ChatBot with custom answers from an App perspective               |
| 9.  | Language Translator  | :green_circle:     | :green_circle:  | :red_circle: M6  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | Translate from one language to another                                   |
| 10. | Sentiment Analyzer   | :green_circle:     | :green_circle:  | :green_circle:   | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | Analyses the Sentiment of a text input. Positve, Neutral, Negative       |
| 11. | Data Extractor       | :orange_circle: O1 | :red_circle: L3 | :red_circle: M7  | :red_circle: P4  | :red_circle: G3  | :red_circle: F4 | :green_circle: | Extract Number, Date, Model (Pojo) from a Text                           |
| 12. | Persistent Store     | :green_circle:     | :green_circle:  | :red_circle: M8  | :red_circle: P5  | :red_circle: G4  | :green_circle:  | :green_circle: | Use a Persistent Store for Chat Memory & Retrieve the Chat based on User.|

## Retrieval Augmented Generation (RAG) Examples


| #   | Example                      | GPT 4o         | Meta Llama3     | Mistral         | Microsoft Phi-3 | Google Gemma    | TII Falcon 2    | Claude 3       | Details                                              |
|-----|------------------------------|----------------|-----------------|-----------------|-----------------|-----------------|-----------------|----------------|------------------------------------------------------|
| 51. | Simple                       | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | Chat Bot Use Case: Car Rental Service                |
| 52. | Segments                     | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | Using Segments: Car Rental Service                   |
| 53. | Query Transformer            | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | Using Query Transformer: Biography                   |
| 54. | Query Router                 | :green_circle: | :red_circle: L4 | :red_circle: M9 | :red_circle: P6 | :red_circle: G4 | :red_circle: F5 | :green_circle: | Query Routing between Car Rental Service & Biography |        
| 55. | Re-Ranking                   | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | Re-Ranking using Cohere API: Car Rental Service      |
| 56. | MetaData                     | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | MetaData (Data Source): Car Rental Service           |
| 57. | Multiple Content Retrievers  | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | Multi Content Retrievers. Car Rental & Biography     |
| 58. | Skipping Content Retrieval   | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | Content Retrieval Skipping: Car Rental Service       |
| 59. | Health Care Diagnosis        | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | Health Care Case Study: Diagnosis Summary & Details  | 

## Top LLM Rankings based on Enterprise Features

| # | Company     | LLM                   | Score | Category |
|---|-------------|-----------------------|-------|----------|
| 1 | Anthropic   | Claude 3 Haiku        | 21/21 | Cloud    |
| 2 | Open AI     | Chat GPT 4o           | 20/21 | Cloud    |
| 3 | Meta        | Llama 3               | 17/21 | Local    |
| 4 | TII         | Falcon 2              | 16/21 | Local    |
| 4 | Google      | Gemma                 | 16/21 | Local    |
| 5 | Microsoft   | PHI 3                 | 15/21 | Local    |
| 6 | Mistral     | Mistral               | 12/21 | Local    |

Note: Cloud-based LLMs will have more than 500 billion parameter support while the local LLMs are mostly based on 8 Billion parameters.

### OpenAI GPT 4o (Omini) Observations

| #  | Example          | Observations                                               |
|----|------------------|------------------------------------------------------------|
| O1 | Data Extractor   | Pojos works with GPT 3.5 only and not with GPT 4o          |

### Meta (Facebook) Llama3 Observations

| #  | Example          | Observations                                               |
|----|------------------|------------------------------------------------------------|
| L1 | Image Generation | No image generation support                                |
| L2 | Tools Annotation | No Support for Tools that helps in querying custom data.   | 
| L3 | Data Extractor   | Data extractor doesnt work cleanly for Date and Pojos      |
| L4 | Query Router     | Query Router doesnt work between different sources         |

### TII Falcon 2 Observations

| #  | Example          | Observations                                               |
|----|------------------|------------------------------------------------------------|
| F1 | Custom Data      | Falcon is completely confused in handling Custom Data.     |
| F2 | Image Generation | No image generation support                                |
| F3 | Tools Annotation | No Support for Tools that helps in querying custom data.   | 
| F4 | Data Extractor   | Data extractor doesnt work cleanly for Time and Pojos      |
| F5 | Query Router     | Query Router doesnt work between different sources         |

### Google Gemma Observations

| #  | Example          | Observations                                               |
|----|------------------|------------------------------------------------------------|
| -  | Image Generation | No image generation support. Created ascii based images.   |
| G1 | Tools Annotation | No Support for Tools that helps in querying custom data.   |
| G2 | Chat Memory      | MemoryID Annotation not working.                           |
| G3 | Data Extractor   | Unable to extract date and Pojos with Dates.               |
| G4 | Persistent Store | Confused about Memory ID in LangChain4J                    |
| G5 | Query Router     | Works only with 1 data set. Car Rental Service.            |


### Microsoft PHI - 3 Observations

| #  | Example          | Observations                                               |
|----|------------------|------------------------------------------------------------|
| P1 | Image Generation | No image generation support                                |
| P2 | Tools Annotation | No Support for Tools that helps in querying custom data.   | 
| P3 | Chat Memory      | Memory ID annotation is not working                        |
| P4 | Data Extractor   | Data extractor doesnt work cleanly for Date and Pojos      |
| P5 | Persistent Store | Confused about Memory ID in LangChain4J                    |
| P6 | Query Router     | Query Router works, but adds un-wanted data (hallucinates) |

### Mistral Observations

| #  | Example          | Observations                                               |
|----|------------------|------------------------------------------------------------|
| M1 | Complex World    | Extremely poor in Word and Math problems                   |
| M2 | Image Generation | No Image generation support                                |
| M3 | Prompt Tempalte  | GPT 4o answers are far more refined compared to Mistral    |
| M4 | Tools Annotation | No Support for Tools that helps in querying custom data.   | 
| M5 | FewShot          | Completely confused! Gives both -ve & +ve responses.       |
| M6 | Lang Translator  | 90% Accuracy                                               |
| M7 | Data Extractor   | Not able to extract Number, Date, Pojos with dates         |
| M8 | Persistent Store | Confused about Memory ID in LangChain4J                    |
| M9 | Query Router     | Query Router doesnt work between different sources         |

## Install Local LLMs

To Install the Local LLMs using Ollama 
1. Meta Llama3
2. Google Gemma
3. Microsoft PHI-3
4. TII Falcon 2
5. Mistral

Check out the <a href="https://github.com/arafkarsh/ms-springboot-324-ai/blob/main/llms/README_LOCAL_LLMS.md">installation guide.</a>

## Get the Keys for Testing Cloud LLMs

### Register to get the API Keys

1. Open AI - ChatGPT (API Key can be created here: https://platform.openai.com/api-keys)
2. Anthropic - Claude 3 (API key can be created here: https://console.anthropic.com/settings/keys)
3. Cohere - (API key here:  https://dashboard.cohere.com/welcome/register)
4. HuggingFace - (API key here: https://huggingface.co/settings/tokens)
5. Rapid - (API key here: https://rapidapi.com/judge0-official/api/judge0-ce)

### Set these Keys in your environment
```
    // API Keys -----------------------------------------------------------------------
    // OpenAI API key here: https://platform.openai.com/account/api-keys
    public static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    // Cohere API key here: // https://dashboard.cohere.com/welcome/register
    public static final String COHERE_API_KEY = System.getenv("COHERE_API_KEY");

    // Anthropic API key here:: https://console.anthropic.com/settings/keys
    public static final String ANTHROPIC_API_KEY = System.getenv("ANTHROPIC_API_KEY");

    // HuggingFace API key here: https://huggingface.co/settings/tokens
    public static final String HF_API_KEY = System.getenv("HF_API_KEY");

    // Judge0 RapidAPI key here: https://rapidapi.com/judge0-official/api/judge0-ce
    

## Gen AI - Code Package Structure 

![Ai-Code](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/diagrams/Ai-Code-Base.jpg)

## Tools Support in LangChain4J

- OpenAiChatModel
- AzureOpenAiChatModel
- LocalAiChatModel
- QianfanChatModel


(C) Copyright 2024 : Apache 2 License : Author: Araf Karsh Hamid

<pre> 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software  
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and  
 * limitations under the License.    
</pre>
