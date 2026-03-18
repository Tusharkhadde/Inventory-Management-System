
# 📦 Inventory Management System

A simple Java-based Inventory Management System built using **Swing (GUI)** and **JDBC**, supporting basic CRUD operations with a cloud database connection.

---

## 🚀 Features

* ➕ Add new products
* 📋 View product list
* ✏️ Update product details
* ❌ Delete products
* 🖥️ Simple and user-friendly GUI using Java Swing
* ☁️ Database connected using NeonDB (PostgreSQL)

---

## 🛠️ Tech Stack

* Java
* Swing (GUI)
* JDBC
* PostgreSQL (NeonDB)

---

## 📁 Project Structure

```
inventory-management-system/
│
├── src/
│   └── main/java/com/inventory/
│       ├── Main.java
│       ├── dao/
│       ├── model/
│       └── util/
│
├── pom.xml
└── README.md
```

---

## ⚙️ Setup & Run

### 1. Clone the repository

```
git clone https://github.com/Tusharkhadde/Inventory-Management-System.git
cd Inventory-Management-System
```

### 2. Configure Database

Update your NeonDB connection string inside:

```
DatabaseConnection.java
```

Example:

```
String url = "jdbc:postgresql://<host>/<database>?sslmode=require";
String user = "<username>";
String password = "<password>";
```

---

### 3. Compile & Run

If using Maven:

```
mvn compile
mvn exec:java -Dexec.mainClass="com.inventory.Main"
```

Or manually:

```
javac Main.java
java Main
```

---

## 📸 Screenshots

<img width="783" height="589" alt="image" src="https://github.com/user-attachments/assets/9d4389b1-58cb-4dc9-99fa-1a9733f587d3" />

---

## ⚠️ Note

* Make sure your database is running and accessible
* Replace credentials with your own NeonDB details
* Do not expose your database password publicly

---

## 📌 Future Improvements

* Add authentication (login system)
* Improve UI design
* Add search and filtering
* Export data to CSV/PDF

---

## 👨‍💻 Author

**Tushar Khadde**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
