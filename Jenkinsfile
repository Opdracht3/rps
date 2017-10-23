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
				sh("sudo docker build . --tag husamay/rps-backend:0.1.${BUILD_NUMBER}")
            }
        }

        stage('Deploy Docker') {
            steps {
                echo 'Deploying 0.1.${BUILD_NUMBER} to repo....'
				sh("sudo docker push husamay/rps-backend:0.1.${BUILD_NUMBER}")
                echo 'Deploying latest tag to repo....'
				sh("sudo docker push husamay/rps-backend")
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
