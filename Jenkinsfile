pipeline {
    agent {
    label 'windows'
    }

    environment {
        GRID_URL = "http://localhost:4444/wd/hub"
    }

    stages {
        stage('Debug Environment') {
            steps {
                echo 'Debugging Jenkins Agent Environment...'
                bat 'whoami'
                bat 'docker --version || echo Docker not installed'
                bat 'docker-compose --version || echo Docker Compose not installed'
                bat 'git --version || echo Git not installed'
            }
        }

        stage('Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                git branch: 'main', credentialsId: 'git-credentials-id', url: 'https://github.com/jstestingacademy/demo.git'
            }
        }

        stage('Run Docker Compose Version Check') {
            steps {
                echo 'Checking Docker Compose version...'
                bat '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker-compose.exe" --version'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                echo 'Starting Selenium Grid with Docker Compose...'
                dir('docker') {  // Ensure this directory contains docker-compose.yml
                    bat '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker-compose.exe" up -d'
                }
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Selenium tests with Maven...'
                bat 'mvn clean test -Dselenium.grid.url=${GRID_URL}'
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                bat 'mvn surefire-report:report-only'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up Docker containers...'
            dir('docker') {  // Ensure this directory contains docker-compose.yml
                bat '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker-compose.exe" down || echo Cleanup failed, ignoring'
            }
        }
    }
}