# Utiliser une image officielle OpenJDK 17 légère
FROM openjdk:17-jdk-slim

# Dossier de travail dans le container
WORKDIR /app

# Copier le jar compilé dans le container
COPY target/book_management.jar app.jar

# Exposer le port sur lequel tourne l'application Spring Boot
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
