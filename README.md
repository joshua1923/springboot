This project uses Java, Maven, Spring Boot, GraphQL
It is a GraphQL app connects and consumes 3 GRPC services. (user, post, comment)
In order to run the app entirely, this app and the 3 services need to be running in one of the following modes:
- all 4 running in the same environment, such as the local machine
- all 4 running within a docker-compose for which you can find instructions in the actual docker-compose file located in ./src/main/docker

## Making sure the app runs on your machine

Make sure you have installed Maven. (not only the embedded versions in the IDE)
Make sure Java is and Maven are up to date on our machine.

## Using the app in graphql playground
You can access the graphql playground UI at the following link. In there you can find all the schemas and start making graphql requests.
URL for the graphql playground UI:
http://localhost:8080/playground

## Running the application LOCALLY
```shell script
mvn spring-boot:run
```

## Packaging the application
```shell script
mvn package
```
It produces the `graphql-springboot-eng-framework.jar` file in the `target` directory.

## Running the application on docker

The main Dockerfile is located the ./src/main/docker directory.
All the information on how to run this app on Docker can be found in the actual Dockerfile

## Running the application on docker-compose

The docker-compose.yml file is located in the ./src/main/docker directory.
All the information on how to run this app together with the related services on docker-compose can be found in the actual docker-compose file.

