pipeline {
    agent any

    environment {
        GRID_URL = "http://localhost:4444/wd/hub"
         PATH = "/usr/local/bin:$PATH" 
    }

    stages {
        stage('Debug Environment') {
            steps {
                echo 'Debugging Jenkins Agent Environment...'
                sh 'whoami'
                sh 'docker --version || echo Docker not installed'
                sh 'docker-compose --version || echo Docker Compose not installed'
                sh 'git --version || echo Git not installed'
            }
        }

        stage('Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                git branch: 'main', credentialsId: 'jstestingacademy', url: 'https://github.com/jstestingacademy/demo.git'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                echo 'Starting Selenium Grid with Docker Compose...'
                dir('docker') {  // Ensure that 'docker' directory is where docker-compose.yml is located
                    withEnv(["PATH+DOCKER=/usr/local/bin"]) {
                        sh "docker-compose up -d"
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Selenium tests with Maven...'
                sh 'mvn clean test -Dselenium.grid.url=${GRID_URL}'
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                sh 'mvn surefire-report:report-only'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up Docker containers...'
            dir('docker') {  // Ensure cleanup is done in the correct directory
                sh 'docker-compose down || true'
            }
        }
    }
}
