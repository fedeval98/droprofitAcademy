
FROM gradle:8.7.0-jdk21-alpine

COPY . .

EXPOSE 8080

RUN gradle build

ENTRYPOINT ["java", "-jar","build/libs/droprofitAcademy-0.0.1-SNAPSHOT.jar"]