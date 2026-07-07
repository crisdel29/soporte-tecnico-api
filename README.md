# API de Soporte Técnico

API RESTful desarrollada con **Java 17** y **Spring Boot 3** para que una empresa de servicios tecnológicos
pueda **registrar, consultar, actualizar y eliminar** solicitudes de soporte técnico de sus clientes,
reemplazando el registro manual en papel/correos.

> Proyecto académico — IDAT — Unidad didáctica: *Desarrollo de los Componentes del Negocio*

---

## 1. Descripción del proyecto

La aplicación simula el backend de un sistema de tickets de soporte. Permite:

- Registrar **clientes** y **técnicos**.
- Crear **solicitudes de soporte** asociadas a un cliente (y opcionalmente a un técnico).
- Consultar, actualizar, cambiar de estado y eliminar solicitudes.
- Todo se almacena **en memoria** (sin base de datos), usando estructuras `Map`/`List`, tal como pide la consigna.
- Validación de datos de entrada y manejo centralizado de errores.
- Documentación interactiva vía Swagger / OpenAPI.

## 2. Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java 17 | Lenguaje base |
| Spring Boot 3.3.4 | Framework principal |
| Spring Web | Construcción de la API REST |
| Spring Validation | Validación de DTOs (`@Valid`, `@NotNull`, `@NotBlank`, `@Email`) |
| springdoc-openapi | Documentación Swagger UI |
| Maven | Gestión de dependencias y build |
| Postman | Pruebas funcionales de los endpoints |

## 3. Estructura del proyecto

```
src/main/java/com/idat/soporte
├── SoporteTecnicoApplication.java     # Clase principal (arranque de Spring Boot)
├── model/                             # Entidades del dominio (POO)
│   ├── Cliente.java
│   ├── Tecnico.java
│   ├── Solicitud.java
│   └── EstadoSolicitud.java           # Enum: PENDIENTE, EN_PROCESO, RESUELTA, CANCELADA
├── dto/                               # Objetos de transferencia + validaciones
│   ├── ClienteDTO.java
│   ├── TecnicoDTO.java
│   ├── SolicitudRequestDTO.java
│   └── SolicitudResponseDTO.java
├── repository/                        # Persistencia simulada en memoria
│   ├── InMemoryRepository.java        # Interfaz genérica (contrato CRUD)
│   ├── AbstractInMemoryRepository.java# Implementación reutilizable con Map + AtomicLong
│   ├── ClienteRepository.java
│   ├── TecnicoRepository.java
│   └── SolicitudRepository.java
├── service/                           # Lógica de negocio (interfaces + impl)
│   ├── ClienteService.java / impl/ClienteServiceImpl.java
│   ├── TecnicoService.java / impl/TecnicoServiceImpl.java
│   └── SolicitudService.java / impl/SolicitudServiceImpl.java
├── controller/                        # Endpoints REST
│   ├── ClienteController.java
│   ├── TecnicoController.java
│   └── SolicitudController.java
├── exception/                         # Manejo centralizado de errores
│   ├── ResourceNotFoundException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java    # @RestControllerAdvice
└── config/
    └── OpenApiConfig.java             # Configuración de Swagger
```

**Arquitectura en capas:** `controller` → `service` → `repository` → `model`.
Cada capa depende solo de la inmediatamente inferior, lo que facilita el mantenimiento y las pruebas.

## 4. Requisitos previos

- JDK 17 o superior instalado (`java -version`)
- Maven 3.8+ instalado (`mvn -version`), o usar el wrapper si se agrega (`./mvnw`)
- Postman (para las pruebas funcionales)

## 5. Instalación y ejecución

```bash
# 1. Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>
cd soporte-tecnico-api

# 2. Compilar el proyecto
mvn clean install

# 3. Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación quedará disponible en:

```
http://localhost:8080
```

Documentación interactiva (Swagger UI):

```
http://localhost:8080/swagger-ui.html
```

## 6. Endpoints principales

### Clientes — `/api/clientes`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/clientes` | Registrar un cliente |
| GET | `/api/clientes` | Listar todos los clientes |
| GET | `/api/clientes/{id}` | Obtener un cliente por id |
| PUT | `/api/clientes/{id}` | Actualizar un cliente |
| DELETE | `/api/clientes/{id}` | Eliminar un cliente |

### Técnicos — `/api/tecnicos`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/tecnicos` | Registrar un técnico |
| GET | `/api/tecnicos` | Listar todos los técnicos |
| GET | `/api/tecnicos/{id}` | Obtener un técnico por id |
| PUT | `/api/tecnicos/{id}` | Actualizar un técnico |
| DELETE | `/api/tecnicos/{id}` | Eliminar un técnico |

### Solicitudes — `/api/solicitudes`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/solicitudes` | Registrar una solicitud (estado inicial: `PENDIENTE`) |
| GET | `/api/solicitudes` | Listar todas las solicitudes |
| GET | `/api/solicitudes?estado=EN_PROCESO` | Listar solicitudes filtradas por estado |
| GET | `/api/solicitudes/{id}` | Consultar el detalle de una solicitud |
| PUT | `/api/solicitudes/{id}` | Actualizar una solicitud |
| PATCH | `/api/solicitudes/{id}/estado?estado=RESUELTA` | Cambiar el estado de una solicitud |
| DELETE | `/api/solicitudes/{id}` | Eliminar una solicitud |

### Ejemplo de body para crear una solicitud (`POST /api/solicitudes`)
```json
{
  "titulo": "No enciende el servidor",
  "descripcion": "El servidor principal no responde desde esta mañana",
  "clienteId": 1,
  "tecnicoId": 1
}
```

### Ejemplo de respuesta de error (validación)
```json
{
  "timestamp": "2026-07-02T10:15:30",
  "status": 400,
  "error": "Error de validacion",
  "message": "Uno o mas campos no cumplen las reglas de validacion",
  "path": "/api/solicitudes",
  "detalles": [
    "titulo: El titulo es obligatorio"
  ]
}
```

## 7. Pruebas funcionales

En la carpeta `/postman` se incluye la colección `Soporte-Tecnico-API.postman_collection.json`
lista para importar en Postman, con solicitudes preconfiguradas para probar todo el CRUD
(clientes, técnicos y solicitudes), incluyendo casos de error (validación y recurso no encontrado).

Pasos:
1. Abrir Postman → **Import** → seleccionar el archivo `.json`.
2. Verificar que la variable de colección `base_url` apunte a `http://localhost:8080`.
3. Ejecutar las peticiones en orden: crear cliente → crear técnico → crear solicitud → consultar → actualizar → cambiar estado → eliminar.
4. Adjuntar las capturas de estas pruebas en el informe técnico.

## 8. Roles del equipo (completar según corresponda)

| Integrante | Rol / Responsabilidad |
|---|---|
| Integrante 1 | Modelado de clases y capa `repository` |
| Integrante 2 | Capa `service` y lógica de negocio |
| Integrante 3 | Capa `controller`, manejo de excepciones, documentación y pruebas |

## 9. Autores
Equipo de desarrollo backend — Escuela de Tecnología, IDAT.
