#Start with a base image containing Java runtime
FROM openjdk:8

#Informatiopn around who maintains the image
MAINTAINER andres.com

#Add the application's jar to the container
COPY target/Instituto-0.0.1-SNAPSHOT.jar Instituto-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar", "Instituto-0.0.1-SNAPSHOT.jar"]