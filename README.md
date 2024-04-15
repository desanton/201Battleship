
# Battleship Full-stack Application

This project is a full-stack Battleship game with a React frontend and a Java backend.

## Project Structure

- `/site`: Contains the React application (frontend)
- `/backend`: Contains the Java application (backend)

## Running the Project

### Backend

First, ensure the backend is running:

1. Navigate to the `backend` folder.
2. Compile and run the Java application:

```bash
javac -d bin -cp "lib/*" src/main/java/com/battleship201/Main.java
java -cp "bin:lib/*" com.battleship201.Main
```

The backend will start and listen for incoming requests on the designated port.

## Frontend
To get the frontend running, follow these steps:
1. Navigate to the site folder.
1. Install the necessary npm packages:

```bash
npm install
```

Start the React development server:

```bash
npm start
```

This will launch the React application in your default web browser. By default, the development server starts on http://localhost:3000.

### Using the Application
With both the backend and frontend services running, you can use the application by accessing http://localhost:3000 in your web browser. The React application will communicate with the backend to perform operations like user registration and game state management.