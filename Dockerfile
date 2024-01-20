# Verwende das offizielle OpenJDK-Basisimage
#FROM openjdk:17

# Setze das Arbeitsverzeichnis
#WORKDIR /usr/src/main

# Use the official Maven image as a parent image
FROM maven:latest as builder

# Set the working directory in the image
WORKDIR /usr/src/main

# Copy the pom.xml file and download the dependencies to cache them
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project source
COPY src /usr/src/main/src

# Build the application
RUN mvn clean package

# For the final image, use a Java runtime
FROM openjdk:latest

# Set the working directory in the runtime image
WORKDIR /usr/src/main

COPY target/usermanager-1.0-SNAPSHOT.jar ./

# Exponiere den Port, auf dem die Anwendung läuft
EXPOSE 8081

# Starte die JAR-Datei
CMD ["java", "-jar", "usermanager-1.0-SNAPSHOT.jar"]
