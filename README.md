# Delivery Optimizer (Minimal CLI)

A minimal Spring Boot CLI app that computes delivery plans for food orders and prints them to stdout.

## Requirements
- Java 17 (Temurin/AdoptOpenJDK recommended)
- Maven 3.8+
- Optional: Docker 24+

## Run locally
```bash
mvn spring-boot:run
```
This executes the `CommandLineRunner` and prints a single-agent/two-orders plan from `DriverRunner`.

## Build a jar
```bash
mvn -DskipTests package
java -jar target/delivery-optimizer-0.0.1-SNAPSHOT.jar
```

## Docker
```bash
docker build -t delivery-optimizer:latest .
docker run --rm delivery-optimizer:latest
```

## Notes
- Distances use a haversine-based estimator at 20 km/h to convert to minutes. 
