FROM eclipse-temurin:25-jdk-alpine AS builder

WORKDIR /app
COPY . .
RUN ./gradlew bootJar -x test

FROM eclipse-temurin:25-jre-alpine AS runner

WORKDIR /app
RUN apk update && apk add curl
COPY --from=builder /app/build/libs/AcLab-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000

ENTRYPOINT ["java", "-jar", "app.jar"]
