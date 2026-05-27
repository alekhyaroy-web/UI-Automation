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

        emailext (
            subject: "UI Automation Suite - Allure Report",

            body: """
Dear Team,

I hope you are doing well.

Please find the latest execution report for the UI Automation Suite below.

Build Details:
- Job Name: ${env.JOB_NAME}
- Build Number: ${env.BUILD_NUMBER}
- Build Status: SUCCESS

Jenkins Build URL:
${env.BUILD_URL}

Allure Report:
${env.BUILD_URL}allure

The report contains:
- Test execution summary
- Passed/Failed test cases
- Execution timeline
- Detailed logs and validations

Please review the report and let me know if any further analysis is required.

Best Regards,
Alekhya Roy
""",

            to: "receiver@gmail.com"
        )

        echo 'Automation Execution Successful'
    }

    failure {

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

            to: "subhajit.pramanik@codecloudds.com"
        )

        echo 'Automation Execution Failed'
    }

    always {
        archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true
    }
}
}