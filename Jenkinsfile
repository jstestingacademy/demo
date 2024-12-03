pipeline {
    agent any

    environment {
        GRID_URL = "http://localhost:4444/wd/hub"
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                git 'https://github.com/jstestingacademy/demo.git'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                echo 'Starting Selenium Grid with Docker Compose...'
                sh 'docker-compose up -d'
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
            sh 'docker-compose down || true'
        }
    }
}
