# 👕 Wardrobe Manager

A clean-architecture full stack application to manage a clothing catalog, featuring basic and professional-grade backend features like layered architecture, unit testing, and containerized PostgreSQL.

---

## 🛠️ Technologies Used

- 💻 **Frontend**: Angular *(planned)*
- 🧠 **Backend**: Spring Boot 3
- 🐘 **Database**: PostgreSQL (Docker)
- 🐳 **Containerization**: Docker
- 🔁 **Migrations**: Flyway
- 🧪 **Testing**: JUnit 5 + Mockito
- 📊 **Validation**: Bean Validation
- 🧰 **Tools**: IntelliJ IDEA, DBeaver, Lombok

---

## 📦 Features

- ✅ Add new clothing items
- ✅ List all items
- ✅ Update clothing information
- ✅ Delete clothing items
- ✅ Required field validation (`name`, `color`, `size`)
- ✅ Enum-based clothing sizes (`sizePP`, `sizeP`, `sizeM`, `sizeG`, `sizeGG`, `sizeXG`)
- ✅ Clean Architecture (domain-driven separation)
- ✅ Unit tests with JUnit and Mockito
- ✅ PostgreSQL via Docker
- 🧪 Coming soon: REST controller tests with MockMvc

---

## 🚀 How to Run Locally

### Requirements

- Docker
- Java 17+
- Node.js + Angular CLI (optional for frontend)

### Step 1: Run PostgreSQL with Docker

```bash
docker run --name roupasdb \
  -e POSTGRES_DB=roupasdb \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=123456 \
  -p 5433:5432 \
  -d postgres
