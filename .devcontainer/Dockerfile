FROM gradle:7.3.3-jdk11

WORKDIR /workspace

COPY . .

RUN \
    apt-get update && \
    apt-get -y install bash 

RUN  gradle --version && java -version
