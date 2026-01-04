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

        stage('Deploy & Dev Tests') {
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

                            # -----------------------------
                            # Smoke / Load tests apenas em DEV
                            # -----------------------------
                            echo "🧪 Iniciando testes de DEV"

                            MAX_WAIT=120
                            INTERVAL=5
                            ELAPSED=0
                            CONTAINER="lending-api-dev"

                            echo "A aguardar serviço ficar HEALTHY..."

                            while [ \$ELAPSED -lt \$MAX_WAIT ]; do
                              STATUS=\$(docker inspect --format='{{.State.Health.Status}}' \${CONTAINER})
                              echo "Status: \$STATUS"

                              if [ "\$STATUS" = "healthy" ]; then
                                echo "Serviço saudável!"
                                break
                              fi

                              if [ "\$STATUS" = "unhealthy" ]; then
                                echo "Serviço unhealthy"
                                docker logs \${CONTAINER}
                                exit 1
                              fi

                              sleep \$INTERVAL
                              ELAPSED=\$((ELAPSED + INTERVAL))
                            done

                            if [ "\$STATUS" != "healthy" ]; then
                              echo "Timeout à espera do serviço"
                              docker logs \${CONTAINER}
                              exit 1
                            fi

                            # Smoke tests
                            chmod +x scripts/smoke-test.sh
                            scripts/smoke-test.sh http://localhost:8080

                            echo "Load tests ignorados em DEV"
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
                    echo "✔ DEV deployment + smoke tests successful"
                }
            }
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
