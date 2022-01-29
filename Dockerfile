FROM adoptopenjdk/openjdk13

WORKDIR /app

# The application's jar file
ARG JAR_FILE=target/personapp-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} personapp.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/personapp.jar"]
