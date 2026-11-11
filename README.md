# Vivaah — AI-Powered Matrimony & Matchmaking Platform

Vivaah is a full-stack matrimony web application that helps users discover profiles, review compatibility reports, send interest requests, communicate through chat, and get matrimony-related guidance from an integrated AI Advisor.

## Features

- **Authentication and accounts** — user registration, login, JWT-based authentication, and password hashing.
- **Profile discovery** — browse and filter matrimony profiles.
- **Compatibility reports** — view match compatibility information and AI-assisted insights where configured.
- **AI Advisor** — ask matrimony-related questions and receive AI-generated guidance using a configured AI provider.
- **Interest requests** — send, receive, and respond to connection requests.
- **Conversations and chat** — conversation history and real-time messaging support.
- **Responsive user interface** — React-based frontend.

## Technology Stack

| Area | Technologies |
|---|---|
| Frontend | React, JavaScript, Vite, CSS |
| Backend | Java 21, Spring Boot 3.5 |
| API | Spring Web, REST APIs |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL 8 |
| Authentication | Spring Security, JWT, PBKDF2 password hashing |
| AI integration | Gemini, OpenAI, or Ollama (provider configuration) |
| Messaging | WebSocket |
| Build tools | Maven Wrapper, npm |

## Project Structure

```text
vivaah-ai-matrimony-platform/
├── backend/
│   ├── src/main/java/com/apnishadi/
│   │   ├── ai/
│   │   ├── auth/
│   │   ├── config/
│   │   ├── connection/
│   │   ├── match/
│   │   └── profile/
│   ├── src/main/resources/
│   ├── docker-compose.yml
│   └── pom.xml
├── frontend/
│   ├── src/
│   ├── index.html
│   └── package.json
├── .env.example
├── .gitignore
└── README.md
```

> The Java package namespace and existing MySQL schema name (`apni_shadi`) are retained to avoid breaking existing backend references and local databases. The user-facing product branding is Vivaah.

## Prerequisites

- JDK 21
- Node.js and npm
- MySQL 8, or Docker Desktop
- Git

## Run Locally (Windows PowerShell)

### 1. Configure environment variables

From the project root, copy the example file for reference:

```powershell
Copy-Item .env.example .env
```

Set your own database credentials and secrets in your local environment. Do not commit `.env`.

For a local MySQL instance, in the backend terminal:

```powershell
$env:DB_NAME = "apni_shadi"
$env:DB_USERNAME = "your_mysql_username"
$env:DB_PASSWORD = "your_mysql_password"
$env:APP_JWT_SECRET = "replace-with-a-private-random-secret-of-at-least-32-characters"
```

To enable the Gemini-powered AI Advisor, also set:

```powershell
$env:GEMINI_API_KEY = "your_gemini_api_key"
$env:AI_PROVIDER = "gemini"
```

Alternative AI providers can be selected through `AI_PROVIDER` and their corresponding environment variables. See `backend/src/main/resources/application.properties`.

### 2. Start the backend

```powershell
cd backend
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
.\mvnw.cmd spring-boot:run
```

The backend uses port `8080` by default.

### 3. Start the frontend

Open a second terminal:

```powershell
cd frontend
npm install
npm run dev
```

Open the local URL printed by Vite in your terminal.

## Database Notes

The backend is configured with Hibernate schema update (`spring.jpa.hibernate.ddl-auto=update`). Configure the database name and credentials through environment variables before starting the backend. The repository includes a Docker Compose configuration for a local MySQL service.

## Security Notes

- Never commit `.env` files, database passwords, API keys, or private JWT secrets.
- Set your own strong `APP_JWT_SECRET` for local testing and deployment.
- The checked-in environment example contains placeholders only.
- Do not use development credentials for a public deployment.
- Review CORS origins, HTTPS/WSS, authentication storage, rate limiting, and data privacy before production use.

## Author

**Ashis Saha**  


---

*Built as a full-stack software engineering project.*
