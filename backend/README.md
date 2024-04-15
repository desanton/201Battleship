# Backend Application

This is the backend service for the Battleship application, which is responsible for handling database operations and serving API requests.

## Requirements

- Java Development Kit (JDK), version 11 or higher
- MySQL server running locally or remotely with the necessary schema and tables created

## Configuration

Before running the backend server, make sure to update the database connection settings to point to your MySQL instance in `Main.java`.

```java
String jdbcUrl = "jdbc:mysql://localhost:3306/BattleshipDB"; // Replace with your database URL
String username = "root"; // Replace with your database username
String password = "password"; // Replace with your database password
```

## Running the Backend
To compile and run the backend service, navigate to the backend directory in your terminal and execute the following commands:

```bash
javac -d bin -cp "lib/*" src/main/java/com/battleship201/Main.java
java -cp "bin:lib/*" com.battleship201.Main
```

This will start the backend server, which will listen for API requests from the frontend.