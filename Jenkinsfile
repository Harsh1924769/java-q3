pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = 'harshya123/jenkins-cicd-demo'
    }

    stages {

        stage('Git Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker build -t ${DOCKERHUB_REPO}:${BUILD_NUMBER} .
                '''
            }
        }

        stage('Tag Docker Image') {
            steps {
                sh '''
                    docker tag ${DOCKERHUB_REPO}:${BUILD_NUMBER} \
                    ${DOCKERHUB_REPO}:latest
                '''
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKERHUB_USER',
                        passwordVariable: 'DOCKERHUB_TOKEN'
                    )
                ]) {
                    sh '''
                        echo "$DOCKERHUB_TOKEN" | docker login \
                        -u "$DOCKERHUB_USER" --password-stdin

                        docker push ${DOCKERHUB_REPO}:${BUILD_NUMBER}
                        docker push ${DOCKERHUB_REPO}:latest

                        docker logout
                    '''
                }
            }
        }
    }
}
