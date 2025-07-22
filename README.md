# Evento Backend - Spring Boot + JWT + MySQL

Este es el backend de VirellaRent para gestión de eventos, construido con Spring Boot y autenticación JWT. Compatible con el frontend en Angular disponible en https://github.com/AnthonyM77CG/eventos-angular

## Tecnologías

- Java 17
- Spring Boot 3
- Spring Security con JWT
- JPA / Hibernate
- MySQL Workbench
- Lombok
- Variables de entorno (.env)
- REST API compatible con Postman e Insomnia

## Instalación

1. Clonar el repositorio

git clone https://github.com/TuUsuario/evento-backend.git  
cd evento-backend

2. Crear archivo .env en la raíz del proyecto (si no está incluido)

Contenido del archivo .env:

DB_URL=jdbc:mysql://localhost:3306/virella2?createDatabaseIfNotExist=false  
DB_USER=grupo2  
DB_PASS=1234  
JWT_SECRET=supersecretkeythatshouldbelongandcomplexsupersecretkeythatshouldbelongandcomplex

3. Ejecutar la aplicación

Puedes usar Spring Tools Suite, IntelliJ o desde VSCode con la extensión Spring Boot.  
Ejecuta la clase BackendApplication.java

Al iniciar, se crean automáticamente los siguientes usuarios:

- Usuario administrador  
  Correo: adminis@gmail.com  
  Contraseña: 1234567  
  Rol: ADMIN

- Usuario normal  
  Correo: usuario@gmail.com  
  Contraseña: 1234567  
  Rol: USER

## Estructura del proyecto

src  
└── main  
    ├── java  
    │   └── com.virellarent.backend  
    │       ├── api                Controladores REST  
    │       ├── config             Seguridad, JWT, filtros  
    │       ├── entities           Entidades JPA  
    │       ├── repositories       Interfaces JpaRepository  
    │       ├── services           Lógica de negocio y JWT  
    │       └── util               Clases auxiliares  
    └── resources  
        └── application.properties (cargado desde .env)

## Seguridad y autenticación

Se usa JWT para proteger las rutas. El token se envía en el encabezado Authorization con prefijo Bearer. Las rutas públicas y protegidas se configuran en SecurityConfig.java.

Rutas públicas:

- POST /api/v1/auth/** (login, registro, refresh)
- POST /api/comentarios/crear (comentario público sin autenticación)

Todas las demás rutas requieren autenticación.

## Pruebas

Los endpoints pueden probarse con Postman o Insomnia.  
Ejemplo de login:

POST /api/v1/auth/login  
Content-Type: application/json  

{
  "correo": "adminis@gmail.com",
  "contraseña": "1234567"
}

Respuesta esperada:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

Ese token debe incluirse en cada solicitud protegida mediante:

Authorization: Bearer eyJ...

## Comentarios públicos (sin autenticación)

El único endpoint sin token:

POST /api/comentarios/crear

Ejemplo de payload:

{
  "nombre": "Juan",
  "correo": "juan@mail.com",
  "contenido": "Buen servicio, lo recomiendo"
}

No requiere autenticación. Está pensado para ser consumido desde el módulo público del frontend.

## Base de datos

El proyecto utiliza MySQL. Se crea automáticamente la base de datos virella2 si no existe.  
Las credenciales se configuran vía .env.

DB_URL=jdbc:mysql://localhost:3306/virella2?createDatabaseIfNotExist=false  
DB_USER=grupo2  
DB_PASS=1234

Las tablas se crean automáticamente mediante Hibernate (ddl-auto).  
Los roles y usuarios iniciales se insertan al arrancar la aplicación mediante CommandLineRunner.

## Integración con el frontend

Este backend funciona con el siguiente frontend en Angular:  
https://github.com/AnthonyM77CG/eventos-angular

Desde Angular, se recomienda usar HttpClient con headers tipo:

Authorization: Bearer <token>

Para los módulos públicos (como el formulario de comentarios), no es necesario enviar token.

## Contacto

Desarrollado por el equipo VirellaRent.  
Administrador por defecto: adminis@gmail.com  
Usuario común por defecto: usuario@gmail.com
