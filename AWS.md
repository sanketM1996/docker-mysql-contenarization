# 🚀 Spring Boot + MySQL Dockerized Application

This project demonstrates how to containerize a Spring Boot application and connect it with a MySQL database using Docker.

---

## 🛠️ Tech Stack

- Java 21  
- Spring Boot  
- MySQL 8  
- Docker  
- Maven  

---

## 📥 Installation (Ubuntu / Linux)

### 🔹 Update System
```bash
sudo apt update && sudo apt upgrade -y
🔹 Install Java 21
sudo apt install openjdk-21-jdk -y
java -version
🔹 Install Maven
sudo apt install maven -y
mvn -version
🔹 Install Docker
sudo apt install docker.io -y
🔹 Start Docker
sudo systemctl start docker
sudo systemctl enable docker
🔹 Run Docker without sudo (Optional)
sudo usermod -aG docker $USER
newgrp docker
📦 Step 1: Build the Application (Maven)

First, build the JAR file using Maven:

mvn clean package

👉 This will generate a JAR file inside:

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

Login into MySQL container:

docker exec -it mysql-container mysql -u root -p

Password:

root

Run:

SHOW DATABASES;
USE devops_db;
SHOW TABLES;
SELECT * FROM users;
🐳 Alternative: Run Using Docker Compose
▶️ Start Application
docker-compose up --build -d
❌ Stop Application
docker-compose down
💣 Remove Everything (Containers + Volumes + Networks)
docker system prune -a --volumes
🔍 Access Application
http://localhost:8081
🧠 Key Concept

Containers in the same Docker network communicate using container names as hostnames.

Example:

mysql-container:3306
❗ Troubleshooting
🔹 Port Already in Use
sudo lsof -i :8081

Kill process:

kill -9 <PID>
🔹 Check Logs
docker logs springboot-app
docker logs mysql-container
🔹 Remove Containers
docker rm -f mysql-container springboot-app
📌 Notes
Ensure Docker is running
Ensure ports 8081 and 3307 are free
Use correct database name (devops_db)
⭐ Author

Sanket Mahajan