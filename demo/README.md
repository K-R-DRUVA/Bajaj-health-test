# Spring Boot Demo Application

A Spring Boot application that demonstrates REST API integration and JSON processing using the org.json library.

## Features

- Spring Boot 3.5.7 with Java 17
- REST API client implementation using RestTemplate
- JSON processing with org.json library
- Automated webhook generation and submission workflow

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## Building the Project

### On Windows:
```powershell
.\mvnw.cmd clean package
```

### On Linux/Mac:
```bash
./mvnw clean package
```

## Running the Application

```powershell
.\mvnw.cmd spring-boot:run
```

Or run the JAR directly:
```powershell
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Project Structure

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── DemoApplication.java    # Main Spring Boot application
│   │   │       └── AppRunner.java          # CommandLineRunner implementation
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/example/demo/
│               └── DemoApplicationTests.java
└── pom.xml
```

## Dependencies

- **spring-boot-starter-web**: Web application support with REST capabilities
- **org.json**: JSON processing library
- **spring-boot-starter-test**: Testing framework

## How It Works

The application runs as a CommandLineRunner that:
1. Generates a webhook URL and access token via REST API
2. Constructs a SQL query for data retrieval
3. Submits the query to the webhook endpoint with authorization

## Author

K R DRUVA (PES2UG22CS245)

## License

This project is created for educational purposes.
