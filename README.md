# Gen Ai - Springboot / Langchain4J Examples

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


| #   | Example                      | GPT 4o         | Meta Llama3     | Mistral         | Microsoft Phi-3 | Google Gemma    | TII Falcon 2    | Details                                              |
|-----|------------------------------|----------------|-----------------|-----------------|-----------------|-----------------|-----------------|------------------------------------------------------|
| 13. | Simple                       | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | Chat Bot Use Case: Car Rental Service                |
| 14. | Segments                     | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | Using Segments: Car Rental Service                   |
| 15. | Query Transformer            | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | Using Query Transformer: Biography                   |
| 16. | Query Router                 | :green_circle: | :red_circle: L4 | :red_circle: M9 | :red_circle: P6 | :red_circle: G4 | :red_circle: F5 | Query Routing between Car Rental Service & Biography |        
| 17. | Re-Ranking                   | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | Re-Ranking using Cohere API: Car Rental Service      |
| 18. | MetaData                     | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | MetaData (Data Source): Car Rental Service           |
| 19. | Multiple Content Retrievers  | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :orange_circle: | Multi Content Retrievers. Car Rental & Biography     |
| 20. | Skipping Content Retrieval   | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | Content Retrieval Skipping: Car Rental Service       |
| 21. | Health Care Diagnosis        | :green_circle: | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | :green_circle:  | Health Care Case Study: Diagnosis Summary & Details  | 

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

## Cloud Based LLMs

Register to get the API Keys 

1. Open AI - ChatGPT (API Key can be created here: https://platform.openai.com/api-keys)
2. Anthropic - Claude 3 (API key can be created here: https://console.anthropic.com/settings/keys)


## Gen AI - Code Package Structure 

![Ai-Code](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/diagrams/Ai-Code-Base.jpg)


## Quick Test after starting the SpringBoot App
![Ai Prompt](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Prompt.jpg)

## Sentiment Analysis using ChatGPT 4o

![Ai Sentiment](https://raw.githubusercontent.com/arafkarsh/ms-springboot-324-ai/main/diagrams/ai/Ai-Ex-Sentiment-2.jpg)

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
