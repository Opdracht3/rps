pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
				sh("chmod +x gradlew")
				sh("./gradlew clean build")
            }
        }
		stage('Package') {
            steps {
                echo 'Building..'
				sh("./gradlew bootRepackage")
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
		stage('Build Docker') {
            steps {
                echo 'Buildin docker....'
				docker.withRegistry('https://hub.docker.com/', 'docker-repo') {

				def customImage = docker.build("rps-backend:0.0.${BUILD_NUMBER}")

				/* Push the container to the custom Registry */
				customImage.push()
		}
            }
        }
		stage('Deploy Hosting') {
            steps {
                echo 'Deploying....'
				sh("sudo sloppy start --var=domain:rps-project.sloppy.zone ./sloppy.json")
            }
        }
    }
}
