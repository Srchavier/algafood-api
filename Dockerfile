FROM gradle:jdk11 as build

WORKDIR /workspace

COPY . .

RUN gradle clean bootJar

# CMD ["tail", "-f", "/dev/null"]

########################################################################

FROM openjdk:11-jdk-slim

WORKDIR /app

RUN  apt update && apt-get install -y openssl && apt-get -y install wget

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY --from=build /workspace/build/libs/*.jar /app/app.jar
COPY --from=build /workspace/run.sh /app/run.sh
	
# CMD ["java", "-jar", "/app/app.jar"]
