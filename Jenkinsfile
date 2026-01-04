pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_DEV  = "docker-compose.dev.yml"
        DOCKER_COMPOSE_PROD = "docker-compose.prod.yml"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Image') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo "🔵 Building PROD image"
                        sh 'docker build -t lending-service:prod .'
                    } else {
                        echo "🟢 Building DEV image"
                        sh 'docker build -t lending-service:dev .'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'main') {
                        echo "🚀 Deploying to PROD"
                        sh """
                            docker-compose -f ${DOCKER_COMPOSE_PROD} down
                            docker-compose -f ${DOCKER_COMPOSE_PROD} up -d --build
                        """
                    } else {
                        echo "🚧 Deploying to DEV"
                        sh """
                            docker-compose -f ${DOCKER_COMPOSE_DEV} down
                            docker-compose -f ${DOCKER_COMPOSE_DEV} up -d --build
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                if (env.BRANCH_NAME == 'main') {
                    echo "✔ PROD deployment successful"
                } else {
                    echo "✔ DEV deployment successful"
                }
            }
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
