# 🚀 Spring Boot + MySQL Dockerized Application

This project demonstrates how to containerize a Spring Boot application and connect it with a MySQL database using Docker.

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot
* MySQL 8
* Docker
* Maven

---

## 📦 Step 1: Build the Application (Maven)

First, build the JAR file using Maven:

```bash
mvn clean package
```

👉 This will generate a JAR file inside:

```
target/*.jar
```

---

## 🐳 Step 2: Build Docker Image

```bash
docker build -t full-stack-app .
```

---

## 🌐 Step 3: Create Docker Network

```bash
docker network create project
```

---

## 🗄️ Step 4: Run MySQL Container

```bash
docker run -d --name mysql-container --network project -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=devops_db -p 3307:3306 mysql:8
```

---

## 🚀 Step 5: Run Spring Boot Application

```bash
docker run -d -p 8081:8080 --name springboot-app --network project -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-container:3306/devops_db -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=root full-stack-app
```

---

## 🔍 Access Application

Open in browser:

```
http://localhost:8081
```

---

## 🧪 Verify Database

Login into MySQL container:

```bash
docker exec -it mysql-container mysql -u root -p
```

Password:

```
root
```

Check database:

```sql
SHOW DATABASES;
USE devops_db;
SHOW TABLES;
SELECT * FROM users;
```

---

## 🧠 Key Concept

> Containers in the same Docker network communicate using container names as hostnames.

Example:

```
mysql-container:3306
```

---

## ❗ Troubleshooting

### 1. Port Already in Use

```bash
netstat -ano | findstr :8080
```

### 2. Check Logs

```bash
docker logs springboot-app
docker logs mysql-container
```

### 3. Remove Containers

```bash
docker rm -f mysql-container springboot-app
```

---

## 📌 Notes

* Ensure Docker is running
* Ensure ports 8081 and 3307 are free
* Use correct database name (`devops_db`)

---

## ⭐ Author

Sanket Mahajan
