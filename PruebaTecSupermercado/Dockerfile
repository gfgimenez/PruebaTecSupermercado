# ETAPA 1: Compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos el contenido de la subcarpeta directamente a /app
# Nota: El punto final indica que lo pegue en el WORKDIR actual
COPY PruebaTecSupermercado/ .

# Ahora Maven encontrará el pom.xml directamente en /app
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el JAR que Maven acaba de generar
COPY --from=build /app/target/*.jar app_pruebatecsuper.jar

EXPOSE 8080

# Configuramos para que use el puerto de Railway
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app_pruebatecsuper.jar"]