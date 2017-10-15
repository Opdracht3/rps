pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
				sh("chmod +x gradlew")
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
