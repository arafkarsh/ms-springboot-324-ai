# Gen Ai - Springboot / Langchain4J Examples

- Java 21
- SpringBoot 3.2.4
- LangChain4J 0.2.0

Generative AI refers to a subset of artificial intelligence that can generate new content based 
on input data. This encompasses models that can create text, images, music, and even videos. 
Examples of generative AI include language models like OpenAI's GPT-3 and DALL-E, which can 
generate human-like text and images from textual descriptions, respectively.

Generative AI models are typically trained on vast datasets and use deep learning techniques to 
learn patterns and structures in the data. They have a wide range of applications, including:

- Natural Language Processing (NLP): Generating human-like text for chatbots, translations, and content creation.
- Creative Arts: Creating artwork, music, and design elements.
- Data Augmentation: Generating additional data for training other machine learning models.
- Healthcare: Assisting in medical imaging and creating personalized treatment plans.

## How LangChain4J Gen AI API Helps Developers Build Spring Boot AI Apps

LangChain4J is a Java library designed to simplify the integration of large language models 
(LLMs) and AI capabilities into Java applications, including those built with Spring Boot. 
Here’s how it helps developers:

- Unified API for LLMs: LangChain4J provides a unified API that supports multiple LLM providers like OpenAI and Google Vertex AI. This abstraction allows developers to switch between different LLMs without changing their codebase significantly.
- Embedding Store Integration: It integrates with various embedding stores, enabling efficient handling of vectorized data. This is particularly useful for retrieval-augmented generation (RAG) tasks, where relevant information is fetched from a knowledge base to enhance AI responses.
- Toolbox of Features: The library includes a comprehensive set of tools for prompt templating, memory management, and output parsing. These tools help in building complex AI applications by providing high-level abstractions and ready-to-use components.
- Spring Boot Integration: LangChain4J supports Spring Boot, making it easier for developers to create robust and scalable AI applications. The integration allows seamless incorporation of AI services into Spring Boot applications, leveraging Spring's dependency injection and configuration management features.
- Examples and Documentation: LangChain4J offers extensive documentation and examples, guiding developers through various use cases and demonstrating how to implement AI-powered functionalities in their applications.

## Case Study: Health Care App - Gen Ai Enabled Diagnosis Microservices

![Ai RAG-0](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/diagrams/Ai-Case-Study-Architecture.jpg)

## Gen AI Examples: Comparison of 8 LLMs (3 Cloud based & 5 Local) on Enterprise Features

Following comparison is based on the features available in LangChain4J API (which supported by OpenAI ChatGPT). These features are essential for Gen AI based Enterprise App Development.


| #   | Example             | GPT 4o             | Meta Llama3     | Mistral          | Microsoft Phi-3  | Google Gemma     | TII Falcon 2    | Claude 3       | Gemini 1.5     |
|-----|---------------------|--------------------|-----------------|------------------|------------------|------------------|-----------------|----------------|----------------|
| 1.  | Hello World         | :green_circle:     | :green_circle:  | :green_circle:   | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | :green_circle: |
| 2.  | Complex World       | :green_circle:     | :green_circle:  | :red_circle: M1  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | :green_circle: |
| 3.  | Custom Data         | :green_circle:     | :green_circle:  | :green_circle:   | :green_circle:   | :green_circle:   | :red_circle: F1 | :green_circle: | :green_circle: |
| 4.  | Image Generation    | :green_circle:     | :red_circle: L1 | :red_circle: M2  | :red_circle: P1  | :orange_circle:  | :red_circle: F2 | :yellow_circle:| :yellow_circle:|
| 5.  | Prompt Template     | :green_circle:     | :green_circle:  | :red_circle: M3  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | :green_circle: |
| 6.  | Tools               | :green_circle:     | :red_circle: L2 | :red_circle: M4  | :red_circle: P2  | :red_circle: G1  | :red_circle: F3 | :green_circle: | :red_circle: G1|
| 7.  | Chat Memory         | :green_circle:     | :green_circle:  | :green_circle:   | :red_circle: P3  | :red_circle: G2  | :green_circle:  | :green_circle: | :red_circle: G2|
| 8.  | FewShot             | :green_circle:     | :green_circle:  | :red_circle: M5  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | :red_circle: G3|
| 9.  | Language Translator | :green_circle:     | :green_circle:  | :red_circle: M6  | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | :green_circle: |
| 10. | Sentiment Analyzer  | :green_circle:     | :green_circle:  | :green_circle:   | :green_circle:   | :green_circle:   | :green_circle:  | :green_circle: | :green_circle: |
| 11. | Data Extractor      | :orange_circle: O1 | :red_circle: L3 | :red_circle: M7  | :red_circle: P4  | :red_circle: G3  | :red_circle: F4 | :green_circle: | :red_circle: G4|
| 12. | Persistent Store    | :green_circle:     | :green_circle:  | :red_circle: M8  | :red_circle: P5  | :red_circle: G4  | :green_circle:  | :green_circle: | :green_circle: |

## Retrieval Augmented Generation (RAG) Examples of 8 LLMs


| #   | Example                     | GPT 4o         | Meta Llama3     | Mistral         | Microsoft Phi-3 | Google Gemma    | TII Falcon 2    | Claude 3       | Gemini 1.5     |
|-----|-----------------------------|----------------|-----------------|-----------------|-----------------|-----------------|-----------------|----------------|----------------|
| 51. | Simple                      | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | :green_circle: |
| 52. | Segments                    | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | :green_circle: |
| 53. | Query Transformer           | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | :green_circle: |
| 54. | Query Router                | :green_circle: | :red_circle: L4 | :red_circle: M9 | :red_circle: P6 | :red_circle: G4 | :red_circle: F5 | :green_circle: | :red_circle: G5|
| 55. | Re-Ranking                  | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | :green_circle: |
| 56. | MetaData                    | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | :green_circle: |
| 57. | Multiple Content Retrievers | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | :green_circle: | :green_circle: |
| 58. | Skipping Content Retrieval  | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | :green_circle: |
| 59. | Health Care App             | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle: | :green_circle: |

## Top LLM Rankings based on Enterprise Features

| # | Rank | Company     | LLM                   | Score | Category |
|---|------|-------------|-----------------------|-------|----------|
| 1 |  1   | Anthropic   | Claude 3 Haiku        | 21/21 | Cloud    |
| 2 |  2   | Open AI     | Chat GPT 4o           | 20/21 | Cloud    |
| 3 |  3   | Meta        | Llama 3               | 17/21 | Local    |
| 4 |  4   | TII         | Falcon 2              | 16/21 | Local    |
| 5 |  4   | Google      | Gemini 1.5 Pro        | 16/21 | Cloud    |
| 6 |  4   | Google      | Gemma                 | 16/21 | Local    |
| 7 |  5   | Microsoft   | PHI 3                 | 15/21 | Local    |
| 8 |  6   | Mistral     | Mistral               | 12/21 | Local    |

Note: Cloud-based LLMs will have more than 500 billion parameter support while the local LLMs are mostly based on 8 Billion parameters.

Checkout more details on <a href="https://github.com/arafkarsh/ms-springboot-324-ai/blob/main/LLM_Testing.md">Testing Scores</a>

## Install Local LLMs

To Install the Local LLMs using Ollama 
1. Meta Llama3
2. Google Gemma
3. Microsoft PHI-3
4. TII Falcon 2
5. Mistral
6. Wizard Math

Check out the <a href="https://github.com/arafkarsh/ms-springboot-324-ai/blob/main/llms/README_LOCAL_LLMS.md">installation guide.</a>

## Get the Keys for Testing Cloud LLMs

### Register to get the API Keys 

1. Open AI - ChatGPT (API Key can be created here: https://platform.openai.com/api-keys)
2. Anthropic - Claude 3 (API key can be created here: https://console.anthropic.com/settings/keys)
3. Google Cloud - (https://console.cloud.google.com/ - Check AiConstants.java for Instructions) 
4. Cohere - (API key here:  https://dashboard.cohere.com/welcome/register)
5. HuggingFace - (API key here: https://huggingface.co/settings/tokens)
6. Rapid - (API key here: https://rapidapi.com/judge0-official/api/judge0-ce)

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
    public static final String RAPID_API_KEY = System.getenv("RAPID_API_KEY");
```

## Gen AI - Code Package Structure 

### Package io.fusion.air.microservice.ai.genai 

1. controllers (Rest Endpoints for testing the examples)
2. core
   1. assistants (Based on LangChain4J AiService)
   2. models (Data Models used in the code)
   3. prompts (Structured Prompts to have specific outputs)
   4. services (LLM Specific Business Logic re-used across all the examples. )
   5. tools (Functions getting invoked based on LLM search)
3. examples (Claude 3, Falcon 2, GPT 4o, Gemini, Gemma, Llama3,  Mistral, Phi-3, Wizard Math)
4. utils (Generic Code to create ChatLanguageModels and Configurations, API Keys and Console Runner)

### Code Structure

![Ai-Code](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/diagrams/Ai-Code-Base.jpg)

## Quick Test after starting the SpringBoot App
![Ai Prompt](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Prompt.jpg)

## Sentiment Analysis using ChatGPT 4o

![Ai Sentiment](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-Sentiment-2.jpg)

## Content Moderation using ChatGPT 4o

![Ai-Content](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-Content-Moderation.jpg)

## ChatBot using RAG (Custom Data) - Case Study: Car Rental Service

### RAG Architecture

Retrieval-Augmented Generation (RAG) enhances the output of large language models (LLMs) by 
incorporating authoritative external knowledge bases. While LLMs are trained on vast datasets 
and utilize billions of parameters to generate responses for tasks like question answering, language 
translation, and text completion, RAG optimizes these outputs by referencing specific, up-to-date
information sources beyond the model's training data. This process significantly extends the capabilities 
of LLMs to cater to specialized domains or an organization's internal knowledge without necessitating 
model retraining. Consequently, RAG provides a cost-effective solution to ensure that the generated 
content remains relevant, accurate, and contextually appropriate.

#### Large Language Models (LLMs) face several challenges:

- They may provide false information when they lack the correct answer.
- They can deliver outdated or generic information when specific, current responses are expected by the user.
- They might generate responses based on non-authoritative sources.
- They can produce inaccurate responses due to terminology confusion, where different training sources use the same terms to describe different concepts.

Retrieval-Augmented Generation (RAG) addresses several challenges associated with LLMs by 
directing the model to fetch relevant information from authoritative, pre-determined knowledge 
sources. This approach allows organizations to exert more control over the content generated by 
the model, ensuring accuracy and relevance. Additionally, it provides users with clearer insights 
into the sources and processes the LLM uses to formulate its responses.

![Ai RAG-1](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/diagrams/Ai-Case-Study-Architecture.jpg)

### Conversation using LLM with (Custom Data) Car Rental Service Agreement

![Ai ChatBot-1](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-RAG-OZO-1.jpg)
![Ai ChatBot-2](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-RAG-OZO-2.jpg)
![Ai ChatBot-3](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-RAG-OZO-3.jpg)

## Data Extractions using ChatGPT 4o

![Ai Sentiment](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-DataExtractor.jpg)

## LangChain4J operates on two levels of abstraction:

- Low level. At this level, you have the most freedom and access to all the low-level components such as ChatLanguageModel, UserMessage, AiMessage, EmbeddingStore, Embedding, etc. These are the "primitives" of your LLM-powered application. You have complete control over how to combine them, but you will need to write more glue code.
- High level. At this level, you interact with LLMs using high-level APIs like AiServices and Chains, which hides all the complexity and boilerplate from you. You still have the flexibility to adjust and fine-tune the behavior, but it is done in a declarative manner.

![LangChain4J](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/langchain4j-components.png)
Read more... <a href="https://docs.langchain4j.dev/intro">LangChain4J Introduction</a>

## Package Structure

![Package Structure](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/MS-Pkg-Structure.jpg)

## Pre-Requisites

1. SpringBoot 3.2.4
2. Java 22
3. Jakarta EE 10 (jakarta.servlet.*, jakarta.persistence.*, javax.validation.*)
4. PostgreSQL Database 14
5. Ollama 0.1.38

By default the app will use H2 In-Memory Database. No Database setup required for this.

## Step 1.1 - Getting Started

1. git clone [https://github.com/arafkarsh/ms-springboot-324-ai.git](https://github.com/arafkarsh/ms-springboot-324-ai.git)
2. cd ms-springboot-324-ai
3. cd database
4. Read the README.md to setup your database (PostgreSQL Database)
5. By Default (Dev Mode) the App will use In-Memory H2 Database

##  Step 1.2 - Compile (Once your code is ready)

### 1.2.1 Compile the Code
Run the "compile" from ms-springboot-324-ai
1. compile OR ./compile (Runs in Linux and Mac OS)
2. mvn clean; mvn -e package; (All Platforms)
3. Use the IDE Compile options

### 1.2.2 What the "Compile" Script will do

1. Clean up the target folder
2. Generate the build no. and build date (takes application.properties backup)
3. build final output SpringBoot fat jar and maven thin jar
4. copy the jar files (and dependencies) to src/docker folder
5. copy the application.properties file to current folder and src/docker folder

In Step 1.2.2 application.properties file will be auto generated by the "compile" script. This is a critical step.
Without generated application.properties file the service will NOT be running. There is pre-built application properties file.

##  Step 1.3 - Run

### 1.3.1 Start the Service
1. run OR ./run (Runs in Linux or Mac OS)
2. run prod (to run the production profile, default is dev profile)
3. mvn spring-boot:run (All Platforms - Profile dev H2 In-Memory Database)
4. mvn spring-boot:run -Dspring-boot.run.profiles=prod (All platforms - Profile prod PostgreSQL DB)

### 1.3.2 Test the Service
1. test OR ./test (Runs in Linux or Mac OS)
2. Execute the curl commands directly (from the test script)

## Chat Models

- OpenAI (Examples available)
- Ollama - run AI models on your local machine (Examples available)
- Azure Open AI
- Amazon Bedrock
    - Cohere's Command
    - AI21 Labs' Jurassic-2
    - Meta's LLama 2
    - Amazon's Titan
- Google Vertex AI Palm
- Google Gemini
- HuggingFace - access thousands of models, including those from Meta such as Llama2
- MistralAI

## Text-to-image Models

- OpenAI with DALL-E (Examples available)
- StabilityAI

## Transcription (audio to text) Models

- OpenAI

## Embedding Models

- OpenAI
- Ollama
- Azure OpenAI
- ONNX
- PostgresML
- Bedrock Cohere
- Bedrock Titan
- Google VertexAI
- Mistal AI

The Vector Store API provides portability across different providers, featuring a novel SQL-like metadata filtering API that maintains portability.

## Vector Databases

- Azure Vector Search
- Chroma
- Milvus
- Neo4j
- PostgreSQL/PGVector
- PineCone
- Redis
- Weaviate
- Qdrant

## Models supported are

- OpenAI
- Azure OpenAI
- VertexAI
- Mistral AI

## Checkout the CRUD Operation Examples 

1. Setting up Postman with REST Endpoints for Testing
2. CRUD Examples
3. JWT Token Examples

Check the <a href="https://github.com/arafkarsh/ms-springboot-324-ai/blob/main/CRUD_Examples.md">CRUD_Examples</a>.md</a>

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
