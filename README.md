# 🏦 Smart Banking Simulator

A full-stack web application that mimics real-world banking functionalities, allowing users to create and manage accounts while performing essential financial operations in a simulated environment.

---

## 🛠️ Tech Stack

| Layer    | Technology              |
|----------|-------------------------|
| Frontend | HTML, CSS, JavaScript   |
| Backend  | Spring Boot             |
| Database | MySQL                   |

---

## 🏗️ Architecture

The application follows a **layered architecture** design, ensuring better separation of concerns and scalability.

```
├── Controller Layer     → Handles incoming HTTP requests and sends responses
├── Service Layer        → Contains business logic and processing rules
└── Repository Layer     → Manages database interactions
```

---

## ✨ Features

- 🔐 Create and manage bank accounts
- 💰 Perform deposit and withdrawal transactions
- 📋 Maintain detailed transaction records
- 📧 Send instant email notifications for every transaction
- 🔒 Secure data handling using MySQL database

---

## 📦 Modules

### 🗂️ Account Management Module
Responsible for handling user account creation, updates, and retrieval of account details.

### 💳 Transaction Processing Module
Manages all financial operations including deposits and withdrawals, while recording important details like:
- Transaction type
- Amount
- Timestamp

### 🔔 Notification System
Integrates an email notification service that automatically sends alerts to users whenever a transaction is made — enhancing transparency and keeping users informed about their account activity in real time.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/smart-banking-simulator.git
   cd smart-banking-simulator
   ```

2. **Configure the database**

   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the app**

   Open your browser and navigate to `http://localhost:8080`

---

## 📁 Project Structure

```
smart-banking-simulator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/banking/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       └── model/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
├── pom.xml
└── README.md
```

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
