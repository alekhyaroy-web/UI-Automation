pipeline {

    agent any

    tools {
        jdk 'JDK25'
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/alekhyaroy-web/UI-Automation.git'
            }
        }

        stage('Clean Project') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Automation Tests') {
            steps {
                bat 'mvn test -Dheadless=true'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false,
                jdk: '',
                results: [[path: 'allure-results']]
            }
        }

    }

    post {

        success {
            echo 'Automation Execution Successful'
        }

        failure {
            echo 'Automation Execution Failed'
        }

        always {
            archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true
        }
    }
}