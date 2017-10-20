# rps
Rock paper scissor

//steps to push code to repo
//gradle clean build
//gradle bootRepackage
//docker build . husamay/rps-backend:0.1
//docker push husamay/rps-backend:0.1

//steps run code from repo
//docker pull husamay/rps-backend:0.1
//docker run --name backend-container -d -p 4040:8080 husamay/rps-backend:0.1