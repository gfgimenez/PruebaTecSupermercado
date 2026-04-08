# ETAPA 1: Compilación (Build)
# Usamos una imagen con Maven y JDK para construir el JAR dentro de la nube
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Esto genera el archivo .jar dentro del contenedor de compilación
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Run)
# Usamos una imagen ligera solo para correr la aplicación
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Aquí está el truco: copiamos el jar generado en la etapa "build"
# Usamos un comodín (*.jar) para que no falle si el nombre cambia un poco
COPY --from=build /app/target/*.jar app_pruebatecsuper.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_pruebatecsuper.jar"]