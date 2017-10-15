pipeline {
    agent any

    stages {
		stage('Example Test') {
            agent { docker 'openjdk:8-jre' } 
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
				sh("${project}/gradlew clean build")
            }
        }
		stage('Package') {
            steps {
                echo 'Building..'
				sh("gradlew bootRepackage")
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
				sh("docker build -t ${project} .")
            }
        }
    }
}