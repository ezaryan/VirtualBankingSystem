
# 💳 Virtual Banking System - Java + MySQL

This project is a simulation of an online banking system built using **Java** and **MySQL**, complete with features like account creation, login, deposit, withdrawal, transfer, transaction history, and admin access.

## 🚀 Features

- User Registration & Login  
- Admin Access (username: `admin`, password: `pass`)  
- Deposit, Withdraw, Transfer  
- Transaction History (Passbook)  
- Input validation & ENUM-based gender field  
- MySQL-backed data persistence  

## 🛠️ Technologies Used

- Java  
- MySQL  
- JDBC  
- Swing / JavaFX (based on UI version)  

## 🧩 Database Setup Instructions

Follow these steps to set up the database for the application:

### 1. Install Requirements

- Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)  
- Install [MySQL](https://dev.mysql.com/downloads/installer/)  

### 2. Configure MySQL

- Open **MySQL** and set a **root user password** (if not already set)  
- Open **Command Prompt** and navigate to:  


C:\Program Files\MySQL\MySQL Server 8.0\bin

````
- Run the following:
```bash
mysql -u root -p
````

* Enter your MySQL root password.

### 3. Create the Database & Tables

Once inside the MySQL shell, copy and paste the following:

```sql
CREATE DATABASE your_database_name;  -- replace with your preferred name

USE your_database_name;

CREATE TABLE users (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50) NOT NULL,
  balance DOUBLE DEFAULT 0,
  phone VARCHAR(15),
  email VARCHAR(255),
  gender ENUM('male', 'female', 'other'),
  wlimit DOUBLE DEFAULT 1000.0
);

CREATE TABLE transactions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  description VARCHAR(255),
  amount DOUBLE NOT NULL,
  balance DOUBLE NOT NULL
);
```

📌 **Note:** Replace `your_database_name` with your actual database name, and update it in the source code accordingly.

## 📂 Running the Project

1. Open the project folder in your IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
2. Update the database name and password in the JDBC connection string in the code.
3. Compile and run the project.
4. Start as:

   * `New Customer` to register.
   * `Existing Customer` to log in again.
   * Use `admin / pass` to access admin functionalities.



## 📝 License

This project is open-source and available under the [MIT License](LICENSE).

```

---

Let me know if you want the `README.md` file in downloadable format or with direct integration into your project!
```
