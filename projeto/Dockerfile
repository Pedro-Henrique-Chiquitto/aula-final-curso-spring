#Etapa de construção
FROM maven:3.4.0-openjdk-21 AS build
#FROM ubuntu:latest AS build

RUN api-get update && apt-get-install -y maven apt-get clean && rm -rf/var/lib/apt/lists/*

#Definir o diretorio dentro do container para a aplicação
WORKDIR /app

#Copiar o pom.xml para o container
COPY pom.xml .
COPY src ./src 

#Executar o Maven para compilar projeto e gerar o jar file
RUN mvn clean package -DskipTests

#Etapa de execução
FROM openjdk:21-slim

#Definir o diretorio de trabalho para a aplicação
WORKDIR /app

#Copiar o JAR construido na etapa anterior
COPY  --from=build /app/target/*.jar app.jar

#Definir a Porta que será utilizada na aplicação
EXPOSE 8080

#Comando para executar a aplicação
ENTRYPOINT [ "java", "-jar", app.jar]



