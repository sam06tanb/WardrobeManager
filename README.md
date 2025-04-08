# Wardrobe Manager 👕

Um projeto full stack simples para gerenciar um catálogo de roupas, com operações básicas de CRUD (criar, listar, editar e excluir). 

### 🛠️ Tecnologias usadas

- 💻 **Frontend**: Angular
- 🧠 **Backend**: Spring Boot
- 🐘 **Banco de Dados**: PostgreSQL
- 🐳 **Containerização**: Docker
- 🧬 **Migrations**: Flyway
- 🔍 **Dev Tools**: DBeaver, Lombok

### 📦 Funcionalidades

- Cadastrar uma nova peça de roupa
- Listar todas as roupas
- Atualizar informações de uma roupa
- Remover roupas cadastradas
- Validação de dados obrigatórios (nome, cor, tamanho via enum)

### 🚀 Como rodar

> Requisitos: Docker, Java 17+, Node.js, Angular CLI

1. **Subir o banco:**
   ```bash
   docker run --name postgres-container \
     -e POSTGRES_DB=roupasdb \
     -e POSTGRES_USER=postgres \
     -e POSTGRES_PASSWORD=123456 \
     -p 5433:5432 \
     -d postgres
