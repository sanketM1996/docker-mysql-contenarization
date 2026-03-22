🚀 Spring Boot + MySQL Dockerized Application

This project demonstrates how to containerize a Spring Boot application and connect it with a MySQL database using Docker.

🛠️ Tech Stack
Java 21
Spring Boot
MySQL 8
Docker
Maven
📥 Installation (Ubuntu / Linux)
🔹 1. Update System
sudo apt update && sudo apt upgrade -y
🔹 2. Install Java 21
sudo apt install openjdk-21-jdk -y
java -version
🔹 3. Install Maven
sudo apt install maven -y
mvn -version
🔹 4. Install Docker
sudo apt install docker.io -y
🔹 5. Start Docker
sudo systemctl start docker
sudo systemctl enable docker
🔹 6. Run Docker without sudo (Optional)
sudo usermod -aG docker $USER
newgrp docker
📦 Project Setup
🔹 Clone Repository
git clone <your-repo-url>
cd your-project-folder
📦 Step 1: Build the Application (Maven)
mvn clean package

👉 This will generate:

target/*.jar
🐳 Step 2: Create Dockerfile
# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
🐳 Step 3: Build Docker Image
docker build -t full-stack-app .
🌐 Step 4: Create Docker Network
docker network create project
💾 Step 5: Create Docker Volume
docker volume create mysql-data
🗄️ Step 6: Run MySQL Container
docker run -d \
--name mysql-container \
--network project \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=devops_db \
-p 3307:3306 \
-v mysql-data:/var/lib/mysql \
mysql:8
🚀 Step 7: Run Spring Boot Application
docker run -d \
--name springboot-app \
--network project \
-p 8081:8080 \
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-container:3306/devops_db \
-e SPRING_DATASOURCE_USERNAME=root \
-e SPRING_DATASOURCE_PASSWORD=root \
full-stack-app
🔍 Access Application

Open in browser:

http://localhost:8081

👉 For AWS EC2:

http://<EC2-PUBLIC-IP>:8081
🧪 Verify Database
docker exec -it mysql-container mysql -u root -p

Password:

root
SHOW DATABASES;
USE devops_db;
SHOW TABLES;
SELECT * FROM users;
🐳 Alternative: Docker Compose (Recommended)
📄 docker-compose.yml
version: "3.8"

services:
  mysql:
    image: mysql:8
    container_name: mysql-container
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: devops_db
    ports:
      - "3307:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  app:
    build: .
    container_name: springboot-app
    depends_on:
      - mysql
    ports:
      - "8081:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/devops_db
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root

volumes:
  mysql-data:
▶️ Run Application
docker-compose up --build -d
❌ Stop Application
docker-compose down
💣 Remove Everything
docker system prune -a --volumes
❗ Troubleshooting
🔹 Port Already in Use
sudo lsof -i :8081
kill -9 <PID>
🔹 Check Logs
docker logs springboot-app
docker logs mysql-container
🔹 Remove Containers
docker rm -f mysql-container springboot-app
🧠 Key Concept

👉 Containers in the same Docker network communicate using container names as hostnames.

Example:

mysql-container:3306
📌 Notes
Ensure Docker is running
Ensure ports 8081 and 3307 are free
Use correct database name (devops_db)
⭐ Author

Sanket Mahajan