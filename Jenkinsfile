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
            withCredentials([usernamePassword(credentialsId: 'email-creds',
                                              usernameVariable: 'EMAIL_USER',
                                              passwordVariable: 'EMAIL_PASS')]) {
                emailext (
                    subject: "UI Automation Suite - Allure Report",
                    body: """
Dear Team,

Please find the latest execution report for the UI Automation Suite below.

Build Details:
- Job Name: ${env.JOB_NAME}
- Build Number: ${env.BUILD_NUMBER}
- Build Status: SUCCESS

Jenkins Build URL:
${env.BUILD_URL}

Allure Report:
${env.BUILD_URL}allure

Best Regards,
Alekhya Roy
""",
                    to: "receiver@gmail.com",
                    from: "${EMAIL_USER}",
                    replyTo: "${EMAIL_USER}"
                )
            }
            echo 'Automation Execution Successful'
        }

        failure {
            withCredentials([usernamePassword(credentialsId: 'email-creds',
                                              usernameVariable: 'EMAIL_USER',
                                              passwordVariable: 'EMAIL_PASS')]) {
                emailext (
                    subject: "UI Automation Suite - Automation Failed",
                    body: """
Dear Team,

The latest UI Automation Suite execution has failed.

Build Details:
- Job Name: ${env.JOB_NAME}
- Build Number: ${env.BUILD_NUMBER}
- Build Status: FAILURE

Please check the Jenkins Console Output and Allure Report for detailed failure analysis.

Jenkins Build URL:
${env.BUILD_URL}

Best Regards,
Alekhya Roy
""",
                    to: "subhajit.pramanik@codecloudds.com",
                    from: "${EMAIL_USER}",
                    replyTo: "${EMAIL_USER}"
                )
            }
            echo 'Automation Execution Failed'
        }

        always {
            archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true
        }
    }
}
