#  Temperature Converter Application

## Assignment Description

This project extends the original Temperature Converter application into a fully localized JavaFX desktop application with database-driven localization, runtime language switching, conversion history tracking, automated testing, code coverage analysis, Docker containerization, Jenkins CI/CD integration, and Kubernetes deployment.

The application allows users to:

- Convert temperatures between Celsius, Fahrenheit and Kelvin.
- Switch the application language at runtime between English, Finnish, Swedish, Japanese and Arabic.
- Load localized unit names and descriptions from a database.
- Store temperature conversion history in a MySQL database.
- Display the five most recent conversions.
- Automatically fall back to English translations when localized database content is unavailable.
- Execute automated unit tests using JUnit 5.
- Generate code coverage reports using JaCoCo.
- Build, test and deploy automatically using Jenkins.
- Package the application using Docker.
- Deploy the application using Kubernetes (Minikube).

###  Assignment Requirements Completed

- JavaFX GUI implementation
- FXML-based user interface
- ResourceBundle localization
- Runtime language switching
- Arabic right-to-left (RTL) support
- Translation-table database localization
- MySQL database integration
- Localization fallback mechanism
- Conversion history tracking
- JUnit 5 testing
- JaCoCo code coverage reporting
- Jenkins CI/CD pipeline
- Docker image creation and deployment
- Kubernetes deployment
- GitHub repository management

---

## Repository Information

### GitHub Repository

https://github.com/Hexdoktor/OTP1_inclass1_assignment

### Docker Hub Repository

https://hub.docker.com/r/hexdoktor/temp_converter_docker

---

## Technologies & Tools Used

### Programming Language

- Java 21

### GUI Framework

- JavaFX
- FXML

### Localization

- Java ResourceBundle
- UTF-8 .properties files

### Database

- MySQL / MariaDB

### Build Tool

- Maven

### Testing Framework

- JUnit 5

### Code Coverage

- JaCoCo

### Version Control

- Git
- GitHub

### Continuous Integration

- Jenkins

### Containerization

- Docker
- Docker Hub

### Container Orchestration

- Kubernetes
- Minikube

### Development Environment

- IntelliJ IDEA

---

## Design Approach & Implementation Method

### System Structure

```text
Main
│
├── Controller
│
├── TemperatureConverter
│
├── DBConnection
│
├── TemperatureRecord
│
├── TemperatureRecordDAO
│
├── UnitTranslation
│
└── UnitTranslationDAO
```

### Main

Loads the JavaFX application, ResourceBundle localization resources and FXML user interface.

### Controller

Handles:

- User interactions
- Runtime language switching
- Arabic RTL support
- Conversion execution
- Conversion history display
- Database-driven localization

### TemperatureConverter

Contains temperature conversion calculations:

- Celsius → Fahrenheit
- Fahrenheit → Celsius
- Kelvin → Celsius
- Celsius → Kelvin
- Fahrenheit → Kelvin
- Kelvin → Fahrenheit
- Extreme temperature detection

### DBConnection

Responsible for establishing and managing connections to the MySQL database.

### TemperatureRecord

Represents a temperature conversion history record.

### TemperatureRecordDAO

Handles:

- Saving conversion history
- Retrieving the latest five conversions

### UnitTranslation

Represents a localized database translation entry.

### UnitTranslationDAO

Handles:

- Retrieving localized unit names
- Retrieving localized descriptions
- English fallback functionality

---

## Database Design

This project uses the **Translation Table Localization Approach**.

### Table: `temperature_unit`

Stores language-independent unit identifiers.

| Field | Type |
|---------|---------|
| id | INT |
| unit_key | VARCHAR(50) |

Example values:

- celsius
- fahrenheit
- kelvin

---

### Table: `unit_translation`

Stores localized names and descriptions.

| Field | Type |
|---------|---------|
| id | INT |
| unit_id | INT |
| lang | VARCHAR(5) |
| name | VARCHAR(100) |
| description | TEXT |

---

### Table: `temperature_record`

Stores conversion history.

| Field | Type |
|---------|---------|
| id | INT |
| input_value | DOUBLE |
| result_value | DOUBLE |
| from_unit_id | INT |
| to_unit_id | INT |
| language | VARCHAR(5) |
| created_at | TIMESTAMP |

---

### Relationships

```text
temperature_unit
       │
       └──────< unit_translation

temperature_unit
       │
       └──────< temperature_record
```

All database tables use UTF-8 (`utf8mb4`) encoding to support English, Finnish, Swedish, Japanese and Arabic characters.

---

## Testing & Quality Assurance

### Manual Testing

The application was tested manually using multiple unit combinations and languages.

Examples:

| Input | From | To | Expected Result |
|---------|---------|---------|---------|
| 0 | Celsius | Fahrenheit | 32°F |
| 100 | Celsius | Kelvin | 373.15K |
| 32 | Fahrenheit | Celsius | 0°C |
| 212 | Fahrenheit | Kelvin | 373.15K |
| 300 | Kelvin | Celsius | 26.85°C |
| 273.15 | Kelvin | Fahrenheit | 32°F |

### Localization Testing

The following localization features were verified:

- English translation loading
- Finnish translation loading
- Swedish translation loading
- Japanese translation loading
- Arabic translation loading
- English fallback functionality
- Arabic RTL layout support

### Automated Unit Testing

JUnit 5 was used to test:

- TemperatureConverter
- TemperatureRecord
- TemperatureRecordDAO
- UnitTranslation
- UnitTranslationDAO
- Database connection functionality

### DAO Testing

The following DAO functionality was verified:

- Database connectivity
- Unit translation retrieval
- English fallback retrieval
- Temperature record storage
- Conversion history retrieval

### JaCoCo Coverage

JaCoCo is configured through Maven and generates reports automatically when tests are run.

Run:

```bash
mvn test
```

Coverage reports are generated in:

```text
target/jacoco
```

The project focuses on achieving high coverage of the core application logic, including conversion calculations, database operations and localization functionality.

---

## How to Run

### Prerequisites

Install:

- Java 21
- Maven
- MySQL or MariaDB
- Git
- IntelliJ IDEA
- Docker Desktop
- Minikube
- kubectl

---

## Database Setup

Execute:

```sql
SOURCE init.sql;
```

The `init.sql` script will:

- Create the database
- Create all required tables
- Insert temperature units
- Insert localized translations
- Configure UTF-8 support

---

## Running the Application

Clone the repository:

```bash
git clone https://github.com/Hexdoktor/OTP1_inclass1_assignment.git
```

Enter the project directory:

```bash
cd OTP1_inclass1_assignment
```

Build the project:

```bash
mvn clean install
```

Run the JavaFX application:

```bash
mvn javafx:run
```

---

## Running Unit Tests

```bash
mvn test
```

---

## Docker

Build image:

```bash
docker build -t hexdoktor/temp_converter_docker .
```

Run image:

```bash
docker run hexdoktor/temp_converter_docker
```

---

## Kubernetes

Start Minikube:

```bash
minikube start
```

Deploy the application:

```bash
kubectl apply -f tempconverter-deployment.yaml
kubectl apply -f tempconverter-service.yaml
```

Verify deployment:

```bash
kubectl get pods
```

```bash
kubectl get deployments
```

```bash
kubectl get services
```

---

## Jenkins Pipeline

A Jenkins Declarative Pipeline is provided through the Jenkinsfile.

The pipeline automates:

- Source code checkout from GitHub
- Maven build process
- Unit testing
- JaCoCo code coverage reporting
- Docker image creation
- Docker image publication to Docker Hub

---

## Database Localization Approach

This project uses the **Translation Table Approach**.

### Schema

The localization structure consists of:

- `temperature_unit`
- `unit_translation`

The `temperature_unit` table stores language-independent unit identifiers. The `unit_translation` table stores translated names and descriptions for each supported language.

Example:

| unit_id | lang | name |
|----------|---------|---------|
| 1 | en | Celsius |
| 1 | fi | Celsius |
| 1 | sv | Celsius |
| 1 | ja | 摂氏 |
| 1 | ar | سيلسيوس |

### Reason For Choosing This Approach

The Translation Table Approach was selected because it provides the best normalization while remaining easy to extend.

Advantages include:

- No duplication of non-text data.
- New languages can be added without modifying the database schema.
- Database maintenance remains simple.
- Supports efficient fallback mechanisms.
- Closely matches localization strategies used in production systems.

When the user changes language:

1. Static interface text is loaded from UTF-8 ResourceBundle property files.
2. Localized unit names and descriptions are loaded from the database.
3. Missing translations automatically fall back to English.

This design separates user interface localization from database content localization while keeping both systems synchronized through the application's language selector.

---

## Author

**Juuso**

SEP1 Individual Assignment
