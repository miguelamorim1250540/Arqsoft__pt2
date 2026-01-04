# -------- BUILD STAGE --------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src ./src
RUN mvn -B clean package -DskipTests

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose internal port
EXPOSE 8080

# Spring profile can be overridden in compose
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java","-jar","app.jar"]
