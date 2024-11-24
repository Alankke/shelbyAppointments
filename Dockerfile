FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/shelbyAppointments-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
