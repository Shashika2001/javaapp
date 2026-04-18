# Java Maven Embedded Tomcat Web App

This project is a simple Java 17 Maven web application using an embedded Tomcat server.

## Features

- Java 17 based project
- Embedded Tomcat server on port 8080
- REST endpoint: `GET /hello`
- Static web page in `webapp/index.html`
- Dockerized build and run
- Jenkins pipeline for CI steps

## Project Structure

- `server/` - contains `Application.java` (main class)
- `webapp/` - contains static `index.html`
- `pom.xml` - Maven dependencies and build settings
- `Dockerfile` - container build/run instructions
- `Jenkinsfile` - CI pipeline script
- `README.md` - setup and usage docs

## Run Locally (Maven)

```bash
mvn clean package
java -jar target/javaapp-1.0.0.jar
```

Open:

- Home page: http://localhost:8080/
- API: http://localhost:8080/hello

Expected API response:

```json
{"message":"Hello DevOps"}
```

## Run with Docker

```bash
docker build -t javaapp:latest .
docker run --rm -p 8080:8080 javaapp:latest
```

## Jenkins Pipeline Stages

1. Checkout code
2. Build app with `mvn clean package`
3. Build Docker image
4. Run Docker container

## Notes

- The Maven build uses the Shade plugin to create an executable fat JAR.
- The source directory is configured as `server/` to match the requested structure.
