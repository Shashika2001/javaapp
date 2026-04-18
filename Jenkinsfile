pipeline{
    agent { label 'Jenkins-Agent'}
    tools {
        jdk 'Java21'
        maven 'Maven3'
    }
    stages{
        stage("Cleanup Workspace"){
            steps {
                cleanWs()
            }
            stage("Checkout from SCM"){
                steps{
                    igt branch: 'main' , credentialsId: 'github' , url: 'https://github.com/Shashika2001/javaapp'
                }
            }
            stage("Build Application"){
                steps{
                    sh "mvn clean package"
                }
            }
            stage("Test application"){
                steps{
                    sh "mvn test"
                }
            }
        }
    }
}
