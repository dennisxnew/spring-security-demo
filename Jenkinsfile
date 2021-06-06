pipeline {
    agent { dockerfile true }
    tools {
        maven 'Maven 3.6.0'
        jdk 'JDK 1.8'
    }
    node {
        stage('Build image') {
           app = docker.build("spring-security-demo")
        }
        stage('Push image') {
            docker.withRegistry('http://registry.local:5000') {
                app.push("spring-security-demo-1.0.0")
                app.push("latest")
            }
        }
    }
}