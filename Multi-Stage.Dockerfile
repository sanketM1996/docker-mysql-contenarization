# ---------- Stage 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copy pom first (for caching dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build JAR
RUN mvn clean package -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy only the built jar from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run app
ENTRYPOINT ["java","-jar","app.jar"]