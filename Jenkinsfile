pipeline {
    agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'JDK 1.8'
    }
    stages {
        stage('Build') {
            steps {
                sh 'docker build'
            }
        }
    }
}