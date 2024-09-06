# Fullstack Application (Frontend: Vite + React, Backend: Java Spring Boot)

This project is a fullstack application consisting of a **Vite React frontend** and a **Java Spring Boot backend**. This README provides steps to run the frontend and backend parts of the application.

## Prerequisites

Before running the application, ensure you have the following installed:

- [Node.js](https://nodejs.org/) (version 16 or above)
- [npm](https://www.npmjs.com/) or [Yarn](https://yarnpkg.com/) package manager
- Java 17 or higher
- Maven (for the Java backend)

## Getting Started

### Step 1: Clone the Repository

Clone this repository to your local machine using Git:

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo

cd front 
npm install
npm run dev


This will start the Vite development server. You should see output similar to th
VITE v2.x.x  ready in xms

  ➜  Local:   http://localhost:5137/
  ➜  Network: use --host to expose



And than go to back directory
cd backend
and run ./mvnw spring-boot:run

Please note: if the Java application does not start on port 8080, 
modify the .env file in the Vite app accordingly. Likewise, if the Vite app 
is running on a different port, update the frontend_url in the application.properties file, 
for example: frontend.url=http://localhost:5173.



The server provides routes to manage customers, their preferences, and addresses, including adding, 
editing, and updating these records. It also provides user registration and authentication.

After authenticating the user on the /authenticate route, the server returns a JWT token 
that is valid for 1 hour. This token must be included in the Authorization header of every 
request requiring authentication. The client cannot access the /customers, /preferences, or 
/addresses routes without providing a valid JWT token.