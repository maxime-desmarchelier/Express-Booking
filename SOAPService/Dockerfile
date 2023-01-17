# the first stage of our build will use a maven 3.6.1 parent image
FROM maven AS MAVEN_BUILD
# copy the pom and src code to the container
WORKDIR /soap
COPY ./ ./
# package our application code
RUN mvn clean package
# the second stage of our build will use open jdk
FROM openjdk
# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /soap/target/SOAPService-0.0.1-SNAPSHOT.jar ./SOAPService.jar
# epose port 8080 to the outside world
EXPOSE 8080
# set the startup command to execute the jar
CMD ["java", "-jar", "/SOAPService.jar"]