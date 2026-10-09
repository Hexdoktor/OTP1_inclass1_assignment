# Temperature Converter Application

## Assignment description
This project extends a basic Temperature Converter application into a JavaFX desktop application with database integration, automated testing, code coverage reporting, Jenkins CI/CD automation, and Docker containerization.

The application allows users to:
  - Convert temperatures between Celsius, Fahrenheit and Kelvin.
  - Store temperature conversion records in a MySQL database.
  - Interact with the application through a JavaFX graphical user interface.
  - Execute automated unit test using JUnit 5.
  - Generate code coverage reports using JaCoCo.
  - Build, test, and deploy the application using Jenkins.
  - Package and distribute the application using Docker.

### Assignment Requirements Completed
  - JavaFX GUI implementation
  - MySQL database integration
  - Two related database objects
  - JUnit unit testing
  - JaCoCo code coverage reporting
  - Jenkins pipeline automation
  - Docker image creation and deployment
  - GitHub repository management

---

## Repository Information
**GitHub Repository**
https://github.com/Hexdoktor/OTP1_inclass1_assignment

**Docker Hub repository**
https://hub.docker.com/r/hexdoktor/temp_converter_docker

---

## Technologies & Tools Used
#### Programming Language
  - Java 21
#### GUI Framework
  - JavaFX
#### Database
  - MySQL / MariaDB
#### Build Tool
  - Maven
#### Testing Framework
  - JUnit 5
#### Code Coverage
  - JaCoCO
#### Version Control
  - Git
  - GitHub
#### Continuous Integration
  - Jenkins
#### Containerization
  - Docker
  - Docker Hub
#### Development Environment
  - IntelliJ IDEA

---

## Design Approach & Implementation Method
### System Structure
```text
Main
│
├── TemperatureConverter
│
├── DBConnection
│
├── TemperatureRecord
│
└── TemperatureRecordDAO
```

### Core Components
#### Temperature Converter
Contains the temperature conversion calculations:
  - Celsius -> Fahrenheit
  - Fahrenheit -> Celsius
  - Kelvin -> Celsius
  - Extreme temperature detection
    
#### Main
Implements the JavaFX graphical user interface.
Features include:
  - Temperature input field
  - Conversion selection drop-down menu
  - Convert button
  - Result display area
    
#### DBConnection
Responsible for establishing and managing the connection to the MySQL database

#### TemperatureRecord
Represents a temperature conversion record stored in the database.

#### TemperatureRecordDAO
Handles database operations related to temperature records

---

## Database Design
### Table: `temperature_unit`
Stores the supported temperature units.

| Field | Type |
|---------|---------|
| id | INT |
| unit_name | VARCHAR(20) |

Example values:
 - Celsius
 - Fahrenheit
 - Kelvin

### Table: `temperature_record`
Stores temperature conversion history.

| Field | Type |
|---------|---------|
| id | INT |
| input_value | DOUBLE |
| result_value | DOUBLE |
| unit_id | INT |
| created_at | TIMESTAMP |

### Relationship

```text
temperature_unit (1)
       │
       │
       └──────< temperature_record (many)
```

This satisfies the assignment requirement of using at least two related database tables.

## Testing & Quality Assurance
### Manual Testing

The user interface was manually tested using various temperature values to verify correct functionality.

| Input | Conversion | Expected Result |
|---------|---------|---------|
| 0°C | Celsius → Fahrenheit | 32°F |
| 100°C | Celsius → Fahrenheit | 212°F |
| 32°F | Fahrenheit → Celsius | 0°C |
| 212°F | Fahrenheit → Celsius | 100°C |
| 300K | Kelvin → Celsius | 26.85°C |
| 273.15K | Kelvin → Celsius | 0°C |

### Automated Unit Testing

JUnit 5 was used to verify the correctness of all conversion methods.

#### fahrenheitToCelsius()

- 32°F → 0°C
- 212°F → 100°C
- -40°F → -40°C

#### celsiusToFahrenheit()

- 0°C → 32°F
- 100°C → 212°F
- -40°C → -40°F

#### kelvinToCelsius()

- 300K → 26.85°C
- 273.15K → 0°C
- 0K → -273.15°C

#### isExtremeTemperature()

- Temperatures below -40°C return `true`
- Temperatures above 50°C return `true`
- Normal temperatures return `false`

### JaCoCo Coverage

JaCoCo was integrated using Maven to generate code coverage reports.

```bash
mvn jacoco:report
```

Reports are generated in:

```text
target/jacoco
```

---

## How to Run
### Prerequisites

Install:
  - Java 21
  - Maven
  - MySQL or MariaDB
  - Git
  - IntelliJ IDEA

---

## Database Setup

Create Database:
```sql
CREATE DATABASE tempconverter;
```

Select database:

```sql
USE tempconverter;
```

Create the temperature_unit table:

```sql
CREATE TABLE temperature_unit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    unit_name VARCHAR(20) NOT NULL
);
```

Insert units:

```sql
INSERT INTO temperature_unit(unit_name)
VALUES
('Celsius'),
('Fahrenheit'),
('Kelvin');
```

Create the temperature_record table:

```sql
CREATE TABLE temperature_record (
    id INT AUTO_INCREMENT PRIMARY KEY,
    input_value DOUBLE,
    result_value DOUBLE,
    unit_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(unit_id)
        REFERENCES temperature_unit(id)
);
```

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

## Generating JaCoCo Reports

The project is configured to generate JaCoCo reports automatically whenever tests are executed.

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

## Jenkins Pipeline
A Jenkins Declarative Pipeline is included in the project through the Jenkinsfile.

The pipeline automates:
  - Source code checkout from GitHub
  - Maven build process
  - JUnit testing
  - JaCoCo code coverage reporting
  - Docker image creation
  - Docker image publication to Docker Hub

---

## 👨‍💻 Author

**Juuso**

SEP1 Individual Assignment
