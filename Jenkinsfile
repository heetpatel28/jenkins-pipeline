pipeline {
    agent any

    tools {
        // This tool name must match the Maven installation configured in Global Tool Configuration
        maven 'maven-3.9'
    }

    environment {
        // Replace with your Docker Registry / Docker Hub username
        IMAGE_NAME = 'your-docker-username/java-microservice'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java Microservice...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker Image...'
                sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Push') {
            steps {
                echo 'Logging into Docker Registry and pushing image...'
                // Using withCredentials to securely login and push to Docker Hub
                withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo "${DOCKER_PASS}" | docker login -u "${DOCKER_USER}" --password-stdin'
                    sh "docker push ${IMAGE_NAME}:${BUILD_NUMBER}"
                    sh "docker push ${IMAGE_NAME}:latest"
                }
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying application (main branch specific execution)...'
                // Spin up container locally as deployment target for demo purposes
                sh 'docker stop java-microservice || true'
                sh 'docker rm java-microservice || true'
                sh "docker run -d --name java-microservice -p 8081:8080 ${IMAGE_NAME}:latest"
                echo 'Application deployed successfully on port 8081.'
            }
        }
    }

    post {
        always {
            echo 'Archiving unit test results...'
            junit 'target/surefire-reports/*.xml'
        }
    }
}
