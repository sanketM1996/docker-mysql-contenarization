🚀 Spring Boot + MySQL Dockerized Application

This project demonstrates how to containerize a Spring Boot application and connect it with MySQL using Docker with persistent storage.

🛠️ Tech Stack

Java 21

Spring Boot

MySQL 8

Docker

Maven

📦 Step 1: Build the Application
mvn clean package

👉 Generates:

target/*.jar
🐳 Step 2: Build Docker Image
docker build -t full-stack-app .
🌐 Step 3: Create Docker Network
docker network create project
💾 Step 4: Create Docker Volume
docker volume create mysql-data
🗄️ Step 5: Run MySQL Container
docker run -d \
--name mysql-container \
--network project \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=devops_db \
-p 3307:3306 \
-v mysql-data:/var/lib/mysql \
mysql:8
🚀 Step 6: Run Spring Boot Application
docker run -d \
-p 8081:8080 \
--name springboot-app \
--network project \
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-container:3306/devops_db \
-e SPRING_DATASOURCE_USERNAME=root \
-e SPRING_DATASOURCE_PASSWORD=root \
full-stack-app
🔍 Step 7: Access Application
http://localhost:8081
🧪 Step 8: Verify Database
docker exec -it mysql-container mysql -u root -p

Password:

root
SHOW DATABASES;
USE devops_db;
SHOW TABLES;
SELECT * FROM users;
🧪 Step 9: Test Data Persistence
Insert data
INSERT INTO users(name,email) VALUES('Sanket','test@gmail.com');
Remove container
docker rm -f mysql-container
Run again (same command)

👉 ✅ Data will still exist

❗ Troubleshooting
Check running containers
docker ps
Check logs
docker logs springboot-app
docker logs mysql-container
⭐ Author

Sanket Mahajan