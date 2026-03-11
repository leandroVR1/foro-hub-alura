# ForoHub - API REST Challenge Alura Latam 🚀

### ✒️ Desarrollado por: Leandro Vargas
**Estudiante de Desarrollo de Software - ITM (Medellín, Colombia)**

ForoHub es una solución backend desarrollada con **Spring Boot** para gestionar tópicos de discusión. Este proyecto simula el funcionamiento interno de un foro, implementando reglas de negocio personalizadas, seguridad robusta mediante tokens JWT y persistencia de datos en MySQL.

---

## 🛠️ Tecnologías y Herramientas

* **Java 17**
* **Spring Boot 3** (Spring Security, Spring Data JPA, Spring Web)
* **MySQL** (Base de datos relacional)
* **Flyway** (Migraciones de base de datos)
* **JWT (JSON Web Token)** (Autenticación estática y segura)
* **Maven** (Gestión de dependencias)
* **SpringDoc / Swagger UI** (Documentación técnica interactiva)

---

## 📋 Funcionalidades del Proyecto

- [x] **Seguridad JWT:** Implementación de un filtro de seguridad para validar tokens en cada petición.
- [x] **CRUD Completo de Tópicos:**
    - `POST`: Registro de dudas con validación de datos.
    - `GET`: Listado paginado y ordenado por fecha.
    - `GET /{id}`: Visualización de un tópico específico.
    - `PUT`: Actualización de títulos y mensajes.
    - `DELETE`: Borrado lógico (marcado como inactivo) para mantener integridad referencial.
- [x] **Validaciones de Negocio:**
    - No se permite el registro de tópicos con el mismo título y mensaje exactos.
    - Validación de campos obligatorios mediante `Bean Validation`.
- [x] **Documentación Interactiva:** Integración con Swagger para pruebas rápidas de los endpoints.

---

## 🚀 Configuración y Ejecución

1. **Base de Datos:**
    - Asegúrate de tener MySQL corriendo.
    - Crea la base de datos: `CREATE DATABASE forohub_db;`.
    - El proyecto utiliza **Flyway**, por lo que las tablas se crearán automáticamente al iniciar.

2. **Variables de Entorno:**
   El proyecto espera las siguientes variables (o puedes ajustarlas en `application.properties`):
    - `DB_PASSWORD`: Tu contraseña de MySQL.
    - `JWT_SECRET`: Tu clave secreta para la firma de tokens.

3. **Ejecutar:**
   ```bash
   ./mvnw spring-boot:run

---

## 🔑 Cómo probar la API (Swagger)

1. Accede a: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
2. Realiza el **Login** en el endpoint `/login` con tus credenciales de usuario.
3. Copia el **token** recibido en la respuesta.
4. Haz clic en el botón **Authorize** (el candado en la parte superior derecha).
5. Pega tu token. La interfaz manejará el esquema **Bearer** automáticamente.
6. ¡Ya puedes realizar peticiones a los endpoints protegidos!

---

## 📊 Estructura de la Base de Datos

El proyecto cuenta con las siguientes tablas principales:

* **usuarios**: Almacena las credenciales y perfiles de acceso.
* **topicos**: Contiene la información de las discusiones, autor, curso y el estado de actividad (**borrado lógico**).

---

