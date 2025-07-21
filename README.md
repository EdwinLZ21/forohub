
# ForoHub API

API REST de foro educativo, desarrollada en Java 17+, Spring Boot, MySQL y autenticación JWT.

---

## 📚 Descripción

ForoHub es un backend que implementa un CRUD de tópicos para un foro, donde los usuarios pueden crear, consultar, actualizar y eliminar tópicos de discusión. Solamente los usuarios autenticados pueden interactuar con las rutas protegidas, gracias a la autenticación por JWT.

---

## 🚀 Tecnologías usadas

- Java 17+
- Spring Boot 3+
- Maven 4+
- Spring Data JPA
- MySQL 8+
- Flyway Migration
- Spring Security
- JWT (Auth0)
- Lombok

---

## ⚙️ Instalación y configuración

1. **Clonar repositorio**
 ```
 git clone https://github.com/tuusuario/forohub.git
cd forohub
```

2. **Crear base de datos MySQL**
   ```
   CREATE DATABASE forohub_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
  

3. **Configurar `application.properties`** en `src/main/resources/`:
    ```
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_PASSWORD
   spring.jpa.hibernate.ddl-auto=validate
   spring.jpa.show-sql=true
   spring.flyway.enabled=true
 
4. **Arrancar la app**
   ```
   mvn spring-boot:run
 
## 🖥️ Endpoints principales

> **Todos menos registro y login requieren autenticación JWT.**

### ✔️ Registro de usuarios
`POST /usuarios/registro`
```
{
"username": "usuario1",
"password": "claveSegura"
}
```

### ✔️ Login (genera JWT)
`POST /login`
```
{
"username": "usuario1",
"password": "claveSegura"
}
```
**Respuesta:**
```
{
"token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### ✔️ Usar el token
Incluye un header en cada request protegida:
```
Authorization: Bearer TU_TOKEN_JWT
```

### ✔️ Crear tópico
`POST /topicos`
```
{
"titulo": "Nueva duda sobre Java",
"mensaje": "¿Cómo funcionan los records?",
"status": "ABIERTO",
"autor": "usuario1",
"curso": "Java 17"
}
```

### ✔️ Listar todos los tópicos
`GET /topicos`

### ✔️ Ver detalle de un tópico
`GET /topicos/{id}`

### ✔️ Actualizar un tópico
`PUT /topicos/{id}`

### ✔️ Eliminar un tópico
`DELETE /topicos/{id}`

---

## 🔒 Seguridad

- Sólo los usuarios autenticados pueden crear, editar o eliminar tópicos.
- El registro y el login son públicos.
- Contraseñas se guardan encriptadas (BCrypt).
- Los tokens JWT tienen expiración configurable y deben viajar en cada request protegida.

---

## 📝 Ejemplo de flujo

1. Registra usuario → `/usuarios/registro`
2. Haz login → `/login` y copia el token
3. Usa `Authorization: Bearer ` en requests a `/topicos`
4. Crea, lista, edita o elimina tópicos desde tu cliente favorito (Postman, Insomnia, curl...)

---

## 👨‍💻 Autor

- Edwin Lopez
- Desafío de Oracle + Alura Latam · 2025

---