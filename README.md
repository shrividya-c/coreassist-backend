# CoreAssist - Backend

**CoreAssist** is an AI-powered personal assistant capable of intelligent chat, image generation, speech-to-text transcription, recipe creation and vacation planning.

This repository contains the **backend** of the application, built using **Spring Boot** that leverages **Spring AI** with the **OpenAI** APIs.

---

## 🧠 Functionalities

CoreAssist supports the following AI-powered features via REST APIs:

1. 💬 Chat responses
2. 🖼️ Image generation
3. 🎙️ Speech-to-text transcription
4. 🥘 Recipe creation
5. 🌍 Vacation planning

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot**
- **Spring AI**
- **OpenAI API** (for chat, image, speech-to-text and generative features)

---

## 📦 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- OpenAI API Key

---

### 🏗️ Build & Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/shrividya-c/coreassist-backend.git
   cd coreassist-backend
   ```

2. **Configure application properties**

   Create or update `src/main/resources/application.properties`:
   ```properties
      spring.ai.openai.api-key=YOUR_OPENAI_API_KEY
      spring.ai.openai.chat.model=gpt-3.5-turbo
      spring.ai.openai.image.model=dall-e-3
   ```

3. **Run the application**
   ```bash
      ./mvnw spring-boot:run
   ```
   The backend server will be available at:
   ```arudino
      http://localhost:8080
   ```

### 🔌 API Endpoints Overview
| Feature           | Endpoint Example               | Method |
| ----------------- |--------------------------------|--------|
| Chat Response     | `/ask-ai/{prompt}`             | GET    |
| Image Generation  | `/generate-image`              | GET    |
| Speech-to-Text    | `/transcribe`                  | POST   |
| Recipe Creation   | `/generate-recipe`             | GET    |
| Vacation Planning | `/vacation-planner`            | GET    |


### 📁 Project Structure
```coreassist-backend/
├── src/
│   ├── main/
│   │   ├── java/com/demo/ChatBot/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   └── ChatBotApplication.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
```
