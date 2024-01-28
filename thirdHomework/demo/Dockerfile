## syntax=docker/dockerfile:1
#
#FROM node:18-alpine
#WORKDIR /app
#COPY . .
#RUN yarn install --production
#CMD ["node", "src/index.js"]
#EXPOSE 3000

#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#COPY ../target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8080

FROM amazoncorretto:17
WORKDIR /app
COPY /target/*.jar app.jar
COPY wineries.json /app/src/main/resources/
#ADD build/libs/*.jar App.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
