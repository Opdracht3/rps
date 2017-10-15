pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
				sh("./bash gradlew clean build")
            }
        }
		stage('Package') {
            steps {
                echo 'Building..'
				sh("./bash gradlew bootRepackage")
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
