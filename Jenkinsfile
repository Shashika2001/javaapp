// Jenkins declarative pipeline for CI/CD:
// 1) Checkout source code
// 2) Build Maven artifact
// 3) Build Docker image
// 4) Run Docker container on port 8080
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pull latest source from the configured SCM.
                checkout scm
            }
        }

        stage('Build Maven Package') {
            steps {
                // Clean and package the application JAR.
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build Docker image from local Dockerfile.
                sh 'docker build -t javaapp:latest .'
            }
        }

        stage('Run Container') {
            steps {
                // Stop and remove existing container if present, then start a fresh one.
                sh 'docker rm -f javaapp || true'
                sh 'docker run -d --name javaapp -p 8080:8080 javaapp:latest'
            }
        }
    }
}
