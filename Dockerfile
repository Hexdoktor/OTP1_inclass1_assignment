FROM maven:3.9.9-eclipse-temurin-21

RUN apt-get update && \
    apt-get install -y \
    libx11-6 \
    libgtk-3-0 \
    libxtst6 \
    libxi6 \
    libxrender1 \
    libxext6 && \
    apt-get clean

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

CMD ["mvn", "javafx:run"]