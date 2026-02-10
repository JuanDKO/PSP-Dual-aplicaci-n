# PSP-Dual Aplicación - Sistema de Gestión de Empleados

Este proyecto es una aplicación backend desarrollada con **Spring Boot** para la gestión de recursos humanos. Implementa un sistema CRUD completo para entidades como Empleados, Departamentos, Puestos, Asistencias e Historial de Salarios.

El objetivo principal de esta iteración ha sido robustecer la seguridad y la integridad de los datos.

## 🛡️ Medidas de Seguridad y Protección de la Información

Se ha implementado un sistema de seguridad en capas para garantizar que la información esté protegida contra accesos no autorizados y datos corruptos.

### 1. Autenticación (Identidad)
Para verificar la identidad de los usuarios que interactúan con la API:
*   **Spring Security:** Se ha integrado como framework de seguridad estándar.
*   **HTTP Basic Authentication:** Se utiliza el esquema de autenticación básica, ideal para APIs REST sencillas. Cada petición que modifica datos debe incluir las credenciales.
*   **Cifrado de Contraseñas:** Aunque los usuarios están actualmente simulados en memoria, sus contraseñas se almacenan utilizando **BCrypt**, un algoritmo de hashing robusto, asegurando que nunca se manejen en texto plano internamente.

### 2. Autorización (Control de Acceso)
No todos los usuarios pueden hacer todo. Se ha configurado un control de acceso basado en métodos HTTP:
*   **Lectura Pública (`GET`):** Cualquiera puede consultar la información (listar empleados, ver departamentos). Esto facilita la transparencia o la integración con sistemas de solo lectura.
*   **Escritura Protegida (`POST`, `PUT`, `DELETE`):** Solo los usuarios autenticados con credenciales válidas pueden crear, modificar o eliminar registros.
    *   Esto previene que usuarios anónimos alteren o destruyan la base de datos.
*   **Denegación por Defecto:** Cualquier ruta nueva que se cree estará protegida por defecto a menos que se configure explícitamente como pública.

### 3. Integridad de Datos (Validación)
Para "proteger" la base de datos de información basura o malformada, se implementó validación estricta en la entrada (Input Validation):
*   **Bean Validation (JSR-380):** Se utilizan anotaciones estándar (`@NotBlank`, `@Size`, `@Positive`, `@Email`) en los DTOs (Data Transfer Objects).
    *   *Ejemplo:* No se puede crear un empleado con un salario negativo, un email inválido o sin DNI.
*   **Validación Automática:** Los controladores utilizan `@Valid` para rechazar automáticamente cualquier petición que no cumpla las reglas *antes* de procesarla.
*   **Manejo de Errores Global:** Un `GlobalExceptionHandler` captura los intentos de envío de datos inválidos y responde con un código `400 Bad Request` detallando exactamente qué campos fallaron, sin exponer detalles técnicos sensibles del servidor (evitando Information Leakage).

## 🚀 Cómo Ejecutar

1.  Clonar el repositorio.
2.  Configurar la base de datos PostgreSQL en `src/main/resources/application.properties` (si es necesario).
3.  Ejecutar el proyecto con Maven o tu IDE favorito.
4.  **Credenciales de Prueba (Simuladas en Memoria):**
    *   **Usuario:** `user` / `password`
    *   **Admin:** `admin` / `admin`
