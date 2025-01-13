FROM openjdk:21-jdk-slim

WORKDIR /app

COPY user-service-api/build/libs/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","app.jar"]


