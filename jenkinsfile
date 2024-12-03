pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
    }

    stages {
        stage('Checkout Code') {
            steps {
                 git url: 'https://github.com/jstestingacademy/demo.git', credentialsId: 'jstestingacademy'
            }
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests with Docker') {
            steps {
                sh "docker-compose up --build --abort-on-container-exit"
            }
        }

        stage('Generate Reports') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'report.html',
                    reportName: 'Cucumber Test Report'
                ])
            }
        }
    }

    post {
        always {
            sh "docker-compose down"
        }
    }
