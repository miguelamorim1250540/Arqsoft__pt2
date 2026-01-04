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
                dir("${WORKSPACE}") {
                    script {
                        if (env.BRANCH_NAME == 'main') {
                            echo "🔵 Building PROD image"
                            sh 'docker build --pull -t lending-service:prod .'
                        } else {
                            echo "🟢 Building DEV image"
                            sh 'docker build --pull -t lending-service:dev .'
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                dir("${WORKSPACE}") {
                    script {
                        echo "🛠 Checking Docker-Compose & running containers"
                        sh 'docker-compose version'
                        sh 'docker ps || true'

                        if (env.BRANCH_NAME == 'main') {
                            echo "🚀 Deploying to PROD"
                            sh """
                                docker-compose -f docker-compose.prod.yml down
                                docker-compose -f docker-compose.prod.yml up -d --build
                            """
                        } else {
                            echo "🚧 Deploying to DEV"
                            sh """
                                docker-compose -f docker-compose.dev.yml down
                                docker-compose -f docker-compose.dev.yml up -d --build
                            """
                        }
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
