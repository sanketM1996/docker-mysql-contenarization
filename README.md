🚀 Spring Boot + MySQL Dockerized Application

This project demonstrates how to containerize a Spring Boot application and connect it with a MySQL database using Docker with persistent storage using Docker Volumes.

🛠️ Tech Stack

Java 21

Spring Boot

MySQL 8

Docker

Maven

📦 Step 1: Build the Application (Maven)
mvn clean package

👉 Generates JAR:

target/*.jar
🐳 Step 2: Build Docker Image
docker build -t full-stack-app .
🌐 Step 3: Create Docker Network
docker network create project
🗄️ Step 4: Create Docker Volume (IMPORTANT ✅)
docker volume create mysql-data

👉 This ensures data persistence even if container is deleted

🗄️ Step 5: Run MySQL Container with Volume
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
🔍 Access Application
http://localhost:8081
🧪 Verify Database
docker exec -it mysql-container mysql -u root -p

Password:

root
SHOW DATABASES;
USE devops_db;
SHOW TABLES;
SELECT * FROM users;
🧪 Test Data Persistence (VERY IMPORTANT 🔥)
1. Insert data
INSERT INTO users(name,email) VALUES('Sanket','test@gmail.com');
2. Remove container
docker rm -f mysql-container
3. Run again (same command with volume)

👉 Data will still exist ✅

🧠 Key Concept

Containers in the same Docker network communicate using container names as hostnames.

Example:

mysql-container:3306
💾 What is Docker Volume?

A Docker volume stores data outside the container filesystem, ensuring data is not lost when containers are removed.

❗ Troubleshooting
1. Port Already in Use
netstat -ano | findstr :8080
2. Check Logs
docker logs springboot-app
docker logs mysql-container
3. Remove Containers
docker rm -f mysql-container springboot-app
4. Check Volume
docker volume ls
docker inspect mysql-data
📌 Notes

Ensure Docker is running

Ensure ports 8081 and 3307 are free

Use correct database name (devops_db)

Always mount volume for production databases

⭐ Author

Sanket Mahajan
