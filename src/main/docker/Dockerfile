# Before creating a docker image run the package command to package the app
# It produces the `graphql-springboot-eng-framework.jar` file in the `target` directory.
# Package command
# mvn package
#
# Build command:
# docker build -f src/main/docker/Dockerfile -t graphql-springboot-api .

# After building the image you can either run it in a docker container
# using the commands below, or you can use a docker compose file to call this image.
# This repo contains a docker compose file in the docker-compose folder. In that folder there is also
# a separate external application.properties file.

# Run in a docker container command in detached mode:
# docker run -i --rm -d --name graphql-springboot-api-Container -p 8080:8080 graphql-springboot-api

FROM openjdk:17
WORKDIR /app
COPY ./target/graphql-springboot-eng-framework.jar ./graphql-springboot-eng-framework.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar /app/graphql-springboot-eng-framework.jar"]