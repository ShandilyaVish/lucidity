# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /opt/app
COPY --from=build /app/target/delivery-optimizer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/opt/app/app.jar"] 