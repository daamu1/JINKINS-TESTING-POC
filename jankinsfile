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

        stage('deploy') {
            steps {
                sh "mvn package"
            }
        }

        stage('Build Docker image'){
            steps {
                sh 'docker build -t saurabh03121999/jinking-docker:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){
            steps {
                withCredentials([string(credentialsId: 'saurabh03121999', variable: 'Tungnath@123')]) {
                    sh "docker login -u saurabhmaithani01921@gmail.com -p ${Tungnath@123}"
                }
            }
        }

        stage('Docker Push'){
            steps {
                sh 'docker push saurabh03121999/jinking-docker:${BUILD_NUMBER}'
            }
        }

        stage('Docker deploy'){
            steps {
                sh 'docker run -itd -p 8090:8090 saurabh03121999/jinking-docker:${BUILD_NUMBER}'
            }
        }

        stage('Archiving') {
            steps {
                archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
