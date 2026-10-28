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
- ✅ REST controller tests with MockMvc
- ✅ Fully dockerized: API + PostgreSQL with Docker Compose

---

## 🚀 How to Run

### Option A: Docker Compose (recommended)

Requirements: Docker with Docker Compose.

```bash
cp .env.example .env      # then set your own DB_PASSWORD
docker compose up -d --build
```

The API starts at `http://localhost:8080` once PostgreSQL passes its health check.
Database data is stored in the named volume `pgdata`, so it survives restarts.

```bash
docker compose logs -f api   # follow API logs
docker compose down          # stop (data is kept)
docker compose down -v       # stop and delete the database volume
```

### Option B: Run the API locally

Requirements: Java 17+ and Docker.

```bash
cp .env.example .env
docker compose up -d db      # PostgreSQL only, exposed on port 5433
./mvnw spring-boot:run       # uses the "dev" profile
```

### Run the tests

```bash
./mvnw test                  # uses an in-memory H2 database
```

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/clothes/add` | Create an item |
| GET | `/clothes/show` | List all items |
| GET | `/clothes/show/{id}` | Get one item |
| PUT | `/clothes/update/{id}` | Update an item |
| DELETE | `/clothes/delete/{id}` | Delete an item |

---

## 🗺️ Roadmap

- [ ] Angular front-end
- [ ] OpenAPI/Swagger documentation
- [ ] Authentication (JWT)
