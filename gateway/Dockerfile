FROM maven:3.8.4-openjdk-17
RUN ["mkdir", "/usr/app"]
COPY . /usr/app
WORKDIR /usr/app
RUN ["mvn", "package", "-Dmaven.test.skip"]
CMD ["java","-jar","/usr/app/target/gateway-0.0.1-SNAPSHOT.jar"]