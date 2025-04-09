# 👕 Wardrobe Manager

A simple full stack application to manage a clothing catalog, featuring basic CRUD operations (Create, Read, Update, Delete) — built with **Angular**, **Spring Boot**, **PostgreSQL**, and **Docker**.

---

## 🛠️ Technologies Used

- 💻 **Frontend:** Angular
- 🧠 **Backend:** Spring Boot (Java 17)
- 🐘 **Database:** PostgreSQL
- 🐳 **Containerization:** Docker
- 🔁 **Migrations:** Flyway
- 🧰 **Dev Tools:** DBeaver

---

## 📦 Features

- Add new clothing items
- List all items
- Update clothing information
- Delete clothing items
- Required fields validation (`name`, `color`, `size`)
- Enum-based clothing sizes:
    - `sizePP`, `sizeP`, `sizeM`, `sizeG`, `sizeGG`, `sizeXG`

---

## 🧱 Architecture Overview

The backend follows **Clean Architecture principles**, separating responsibilities into layers:

---

## 🚀 How to Run Locally

### ✅ Requirements:
- Docker
- Java 17+
- Node.js + Angular CLI

### 🐘 Start PostgreSQL with Docker:

```bash
docker run --name roupasdb \\
  -e POSTGRES_DB=roupasdb \\
  -e POSTGRES_USER=postgres \\
  -e POSTGRES_PASSWORD=123456 \\
  -p 5433:5432 \\
  -d postgres