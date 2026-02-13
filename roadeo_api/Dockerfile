# Build maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /roadeo_api
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Distribution du fichier java
FROM eclipse-temurin:17-jre
WORKDIR /roadeo_api
COPY --from=build /roadeo_api/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]