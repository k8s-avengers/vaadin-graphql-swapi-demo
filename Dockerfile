# Running stage: the part that is used for running the application
FROM eclipse-temurin:21-jre
RUN useradd -m myuser
USER myuser
EXPOSE 8080
EXPOSE 9000
ENV APP_JAVA_OPTS=""
COPY target/*.jar /usr/app/app.jar
CMD java $APP_JAVA_OPTS -jar /usr/app/app.jar
