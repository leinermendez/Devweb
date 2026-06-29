# DevWeb

Plataforma freelance para profesionales de tecnología. Permite a freelancers publicar su portafolio y servicios, y a clientes contratarlos, calificarlos y dejar reseñas.

## Integrantes

- Leiner Méndez Jiménez - C14696 (Líder)
- Brandon Víquez Camacho - C4L087
- Joshua Rojas Solano - C26879

## Tecnologías

- **Backend:** Java 21, Spring Boot 4.0.6, Spring Data JPA, Hibernate
- **Base de datos:** PostgreSQL (Neon)
- **Frontend:** HTML5, CSS3, JavaScript, Bootstrap 5
- **Despliegue:** Render (backend + frontend)
- **Herramientas:** IntelliJ IDEA, VS Code, Postman, GitHub

## URLs en producción

- **Backend:** https://devweb-3lsj.onrender.com
- **Frontend:** https://devweb-1.onrender.com

## Estructura del proyecto
src/main/java/ucr/ac/cr/Devweb/

├── config/        # Configuración CORS

├── controller/     # Endpoints REST

├── dto/           # Objetos de transferencia de datos

├── enums/         # Enumerados (Role, Category, RequestStatus, etc.)

├── model/         # Entidades JPA

├── repository/    # Interfaces JpaRepository

└── service/       # Lógica de negocio
frontend/

├── css/           # Estilos

├── js/            # api.js con la URL base del backend

├── users/         # login, register, profile

└── *.html         # index, freelancer, admin

## Módulos implementados

- **Usuarios:** registro, login, perfil, roles (CLIENT, FREELANCER, ADMIN)
- **Portafolio:** publicación de proyectos por freelancers
- **Servicios:** publicación de servicios con precio y categoría
- **Solicitudes de trabajo:** flujo completo (pendiente, aceptado, rechazado, completado)
- **Reseñas:** calificación de freelancers por clientes
- **Verificación:** insignia de confianza aprobada por administrador
- **Reportes:** reporte de proyectos, servicios y usuarios para moderación

## Cómo ejecutar el proyecto localmente

### Backend

1. Clonar el repositorio
2. Configurar las variables de entorno en `application.properties` o como variables del sistema:
    - `DB_URL`
    - `DB_USERNAME`
    - `DB_PASSWORD`
3. Ejecutar `DevwebApplication.java` desde IntelliJ, o:
   mvn clean install

mvn spring-boot:run
4. El backend corre en `http://localhost:8080`

### Frontend

1. Abrir la carpeta `frontend` en VS Code
2. Abrir `login.html` con la extensión Live Server
3. Verificar que `js/api.js` apunte a la URL correcta del backend

## Colección Postman

Disponible en la carpeta `/postman` del repositorio.

## Estado del proyecto

Sistema funcional con todos los módulos backend probados y desplegados en producción. Frontend completo con autenticación, feed de publicaciones, perfiles, servicios, solicitudes, reseñas, verificación y sistema de reportes.

### Pendiente para futuras iteraciones

- Autenticación con JWT y Spring Security
- Autorización por roles a nivel de endpoint
- Sistema de mensajería interna