# Etapa de construção
FROM maven:3.9.6-openjdk-21 AS build

# Atualizar pacotes e instalar dependências (caso necessário)
RUN apt-get update && apt-get install -y maven && apt-get clean && rm -rf /var/lib/apt/lists/*

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos do projeto
COPY pom.xml .
COPY src/ ./src/

# Executar o Maven para compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:21-slim

# Definir o diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR construído na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Definir a porta que será exposta pelo container
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
