pipeline {
    agent any

    stages {
		stage('Checkout') {
			// Pull the code from the repo
			steps {
				checkout scm
			}
		}
        stage('Build') {
            steps {
                echo 'Building..'
				sh("gradlew clean build")
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