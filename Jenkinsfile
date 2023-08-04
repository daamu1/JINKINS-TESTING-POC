pipeline {
    agent any
    stages {
        stage('Compile and Clean') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test site"
            }

            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh "mvn package"
            }
        }

        stage('Build Docker Image'){
            steps {
                sh 'docker build -t saurabh03121999/jinking-docker:${BUILD_NUMBER} .'
            }
        }

       stage('Docker Login'){

                 steps {
                      withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                         sh "docker login -u saurabh03121999 -p ${Dockerpwd}"
                     }
                 }
             }

        stage('Docker Push'){
            steps {
                sh "docker push saurabh03121999/jinking-docker:${BUILD_NUMBER}"
            }
        }

        stage('Docker Deploy'){
            steps {
                sh "docker run -d -p 8090:8090 saurabh03121999/jinking-docker:${BUILD_NUMBER}"
            }
        }

        stage('Archiving') {
            steps {
                archiveArtifacts 'target/*.jar'
            }
        }
    }
}
