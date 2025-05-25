# Plataforma de Renta de Herramientas y Equipos de Construcción 🛠️

## Descripcion.

Este proyecto tiene como objetivo desarrollar una Plataforma de Renta de Herramientas y Equipos de Construcción, donde los proveedores puedan registrar su inventario y los clientes puedan alquilar herramientas, programar entregas y gestionar pagos de forma eficiente.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)](https://www.postgresql.org/)

> **Frontend Repository**: [GitHub Link](https://github.com/Jhormancastella/Proyecto_RentaHerramientas_Frontend_Jhormancastellanos_LuisTalero_LisethArevalo)

## 🚀 Ejecución del Proyecto

## clonar los siguientes repositorios

```bash
# Backend
git clone https://github.com/Jhormancastella/Proyecto_RentaHerramientas_backend_Jhormancastellanos_LuisTalero_LisethArevalo.git
mvn spring-boot:run
```

```bash
# Frontend
git clone https://github.com/Jhormancastella/Proyecto_RentaHerramientas_Frontend_Jhormancastellanos_LuisTalero_LisethArevalo.git
open index.html
```

---

## 📊 Diagrama de Flujo del Backend 

```mermaid
%% @theme default
flowchart TD
    %% Dominio (Entidades)
    subgraph "Dominio (Core)"
        A((🛠️ Herramienta))
        B((👤 Usuario))
        C((📅 Alquiler))
        D((💵 Pago))
    end

    %% Capa de Aplicación
    subgraph "Capa de Aplicación"
        E[[🛠️ HerramientaService]]
        F[[👤 UsuarioService]]
        G[[📅 AlquilerService]]
        H[[💵 PagoService]]
    end

    %% Capa de Infraestructura
    subgraph "Capa de Infraestructura"
        subgraph "Repositorios JPA"
            I[[🗃️ HerramientaRepository]]
            J[[👤 UsuarioRepository]]
            K[[📅 AlquilerRepository]]
            L[[💵 PagoRepository]]
        end
        subgraph "Seguridad"
            M[[🔒 JwtTokenFilter]]
            N[[🛡️ SecurityConfig]]
        end
    end

    %% API REST
    subgraph "Controladores"
        O[[🛠️ HerramientaController]]
        P[[👤 AuthController]]
        Q[[📅 AlquilerController]]
        R[[💵 PagoController]]
    end

    %% Configuración
    subgraph "Configuración"
        S[[⚙️ ApplicationConfig]]
        T[[📄 application.properties]]
    end

    %% Punto de entrada
    U[[🏁 RentadeherramientasApplication]]

    %% Relaciones
    U --> S
    S --> T
    O --> E
    P --> F
    Q --> G
    R --> H
    E --> A & I
    F --> B & J
    G --> C & K
    H --> D & L
    I & J & K & L --> PostgreSQL((🛢️ PostgreSQL))
    M --> N
    P --> M
```

## 📊 Diagrama de Flujo del Frontend

```mermaid
%% @theme default
flowchart TD
    %% Páginas HTML
    subgraph "Vistas"
        A((🏠 index.html))
        B((🔑 login.html))
        C((👤 user.html))
        D((🏭 provider.html))
        E((👑 admin.html))
        F((📝 register.html))
    end

    %% Lógica JavaScript
    subgraph "Scripts"
        G[[🔑 login.js]]
        H[[👤 user.js]]
        I[[🏭 provider.js]]
        J[[👑 admin.js]]
        K[[📝 register.js]]
        L[[🛠️ productos.js]]
    end

    %% Servicios
    subgraph "API"
        M[[🌐 api.js]]
        N[[🔄 environment.js]]
    end

    %% Estilos
    subgraph "CSS"
        O[[🎨 styles.css]]
    end

    %% Flujo Principal
    A -->|"Iniciar Sesión"| B
    B --> G
    G -->|"POST /api/auth/login"| Backend((🚀 Backend))
    G -->|"Redirige por Rol"| C & D & E
    C --> H
    D --> I
    E --> J
    F --> K
    H & I & J & K & L --> M
    M -->|"Fetch/Axios"| Backend
    O --> A & B & C & D & E & F
```

## 📊 Diagrama Entidad-Relación (ER)

```mermaid
erDiagram
    USERS {
        bigint id PK
        boolean active
        string address
        string email
        string first_name
        string last_name
        string password
        string phone
        string username
    }

    ADMINS {
        bigint id PK
        boolean active
        timestamp created_at
        string email
        string first_name
        timestamp last_login
        string last_name
        string password
        string phone
        timestamp updated_at
        string username
    }

    ROLES {
        bigint id PK
        string name
    }

    TOOLS {
        bigint id PK
        boolean available
        string category
        string description
        string image_url
        string name
        numeric rental_price
        string serial_number
        integer stock
    }

    ALQUILERES {
        bigint id PK
        numeric costo_total
        string estado
        timestamp fecha_actualizacion
        timestamp fecha_creacion
        timestamp fecha_fin
        timestamp fecha_inicio
        string observaciones
        bigint tool_id FK
        bigint user_id FK
    }

    RESERVATIONS {
        bigint id PK
        timestamp created_at
        timestamp end_date
        string notes
        timestamp start_date
        string status
        numeric total_amount
        timestamp updated_at
        bigint tool_id FK
        bigint user_id FK
    }

    INVOICES {
        bigint id PK
        timestamp created_at
        timestamp due_date
        string invoice_number
        timestamp issue_date
        string notes
        string status
        numeric subtotal
        numeric tax_amount
        numeric total_amount
        timestamp updated_at
        bigint reservation_id FK
    }

    PAYMENTS {
        bigint id PK
        numeric amount
        timestamp created_at
        string method
        timestamp payment_date
        string payment_details
        string status
        string transaction_id
        timestamp updated_at
        bigint reservation_id FK
    }

    DAMAGE_REPORTS {
        bigint id PK
        string description
        date report_date
        string reported_by
        string status
        bigint tool_id FK
    }

    PRODUCTOS {
        bigint id PK
        string categoria
        string descripcion
        boolean disponible
        string imagen_url
        string nombre
        double precio
        integer stock
    }

    PROVEEDORES {
        bigint id PK
        boolean activo
        string ciudad
        string contacto
        string email
        string nombre
        string telefono
    }

    NOTIFICATIONS {
        bigint id PK
        timestamp created_at
        string link
        string message
        timestamp read_at
        string status
        string title
        string type
        timestamp updated_at
        bigint user_id FK
    }

    USER_ROLES {
        bigint user_id PK,FK
        bigint role_id PK,FK
    }

    ADMIN_ROLES {
        bigint admin_id PK,FK
        bigint role_id PK,FK
    }

    USERS ||--o{ ALQUILERES : "realiza"
    TOOLS ||--o{ ALQUILERES : "es_alquilada"
    USERS ||--o{ RESERVATIONS : "hace"
    TOOLS ||--o{ RESERVATIONS : "es_reservada"
    RESERVATIONS ||--|| INVOICES : "genera"
    RESERVATIONS ||--|| PAYMENTS : "tiene"
    TOOLS ||--o{ DAMAGE_REPORTS : "tiene_reportes"
    USERS ||--o{ NOTIFICATIONS : "recibe"
    USERS }|--|| USER_ROLES : "tiene"
    ROLES }|--|| USER_ROLES : "asignado"
    ADMINS }|--|| ADMIN_ROLES : "tiene"
    ROLES }|--|| ADMIN_ROLES : "asignado"
```

## 🔐 Credenciales de Prueba Automáticas

Al iniciar el servidor, se crean estos usuarios (generados por `data.sql`):

| Rol           | Username     | Password       |
| ------------- | ------------ | -------------- |
| **Admin**     | `admin`      | `admin123`     |
| **Proveedor** | `proveedor1` | `proveedor123` |
| **Cliente**   | `user1`      | `password1`    |

---

---

## 🛠️ Instalación y Configuración del Proyecto

### 📋 Requisitos Previos

- **Java JDK 21** ([Descargar](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html))
- **PostgreSQL 16** ([Descargar](https://www.postgresql.org/download/))
- **Maven 3.9+** ([Instrucciones](https://maven.apache.org/install.html))
- **Live Server**
- **Visual Studio Code** (Recomendado) o cualquier IDE para Java/Web

### 🔧 Pasos para Configurar el Proyecto

#### 1. Configurar Base de Datos (PostgreSQL)

1. Crear una base de datos llamada `renta_herramientas`
2. Configurar credenciales en `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rentadeherramientas
spring.datasource.username=Postgres
spring.datasource.password=tu_contraseña_postgres
spring.jpa.hibernate.ddl-auto=update
```

#### 2. Ejecutar el Backend (Spring Boot)

```bash
cd Proyecto_RentaHerramientas_backend_Jhormancastellanos_LuisTalero_LisethArevalo
mvn spring-boot:run
```

✅ El servidor se iniciará en `http://localhost:8080`

#### 3. Ejecutar el Frontend

1. Abrir el proyecto frontend en VSCode
2. Instalar la extensión **Live Server** (si no la tienes)
3. Abrir `index.html` y hacer clic en **Go Live** (esquina inferior derecha de VSCode)
4. Se abrirá automáticamente en `http://localhost:5500`

---

## 🖥️ Flujo de Ejecución del Sistema

1. **Página de Bienvenida** (`index.html`):
   - Interfaz inicial con logo y descripción del sistema
   - Botón "registro" que redirige a `/register.html`

2. **Login**:
   - Introducir credenciales de prueba (admin/admin123, etc.)
   - El frontend consume el endpoint `POST /api/auth/Authenticate`

3. **Redirección según Rol**:
   - `ADMIN` → Panel de administración (`/admin/admin.html`)
   - `PROVEEDOR` → Gestión de herramientas (`/proveedor/provider.html`)
   - `CLIENTE` → Catálogo de herramientas (`/cliente/user.html`)

---

## 🚦 Estructura de Archivos (Frontend)

```plaintext
📦 PROYECTO_RENTAHERRAMIENTAS_FRONTEND_JHORM...
│
├── 📂 css/ # 🎨 Estilos globales
│ └── 🌀 styles.css # (Archivo principal)
│
├── 📂 images/ # 🖼️ Assets visuales
│ └── 📄 .gitkeep # (Carpeta vacía)
│
├── 📂 js/ # 🧠 Lógica JavaScript
│ ├── 📜 admin.js # 👨💼 Panel administrador
│ ├── 📜 charts.js # 📊 Gráficos (opcional)
│ ├── 📜 environment.js # 🌱 Config variables
│ ├── 📜 login.js # 🔑 Autenticación
│ ├── 📜 productos.js # 🛠️ Gestión de herramientas
│ ├── 📜 provider.js # 🏭 Panel proveedor
│ ├── 📜 register.js # 📝 Registro usuarios
│ ├── 📜 user.js # 👤 Panel cliente
│ └── 📜 welcome.js # 🏠 Página inicial
│
├── 📂 media/ # 📁 Multimedia adicional
│ └── 📄 .gitkeep # (Carpeta vacía)
│
├── 📂 pages/ # 🖥️ Vistas HTML
│ ├── 📄 admin.html # 👨💼 Dashboard admin
│ ├── 📄 login.html # 🔑 Inicio de sesión
│ ├── 📄 provider.html # 🏭 Vista proveedor
│ ├── 📄 register.html # 📝 Registro
│ ├── 📄 user.html # 👤 Perfil cliente
│ └── 📄 index.html # 🏠 Página principal
│
├── 📂 api/ # 🌐 Conexión backend
│ └── 📜 api.js # (Fetch/Axios config)
│
├── 📂 bootstrap/ # 🅱️ Estilos Bootstrap
│ └── 📄 bootstrap.min.css # (Si se usa)
│
└── 📄 README.md # 📖 Documentación
```

## 🗂️ Estructura del Proyecto (Backend)

```plaintext
📦 rentadeherramientas/
├── 📂 src/
│ ├── 📂 main/
│ │ ├── 📂 java/
│ │ │ └── 📂 com.rentadeherramientas.rentadeherramientas/
│ │ │ ├── 📂 Config/ # ⚙️ Configuraciones
│ │ │ ├── 📂 application.services/ # 🛠️ Servicios
│ │ │ ├── 📂 domain.entity/ # 🏷️ Entidades JPA
│ │ │ ├── 📂 dto/ # 📦 Objetos de transferencia
│ │ │ ├── 📂 request/ # 📥 Peticiones HTTP
│ │ │ ├── 📂 response/ # 📤 Respuestas HTTP
│ │ │ ├── 📂 exceptions/ # ❌ Excepciones
│ │ │ ├── 📂 exception/ # 🚨 Manejo de errores
│ │ │ ├── 📂 infrastructure/ # 🌐 Infraestructura
│ │ │ └── 🎯 RentadeherramientasApplication.java # 🚀 Clase principal
│ │ └── 📂 resources/ # 📄 Recursos
│ └── 📂 target/ # 🏗️ Generado por Maven
│ └── 📂 generated-sources/annotations/ # 🔄 Código generado
├── 📂 .mvn/ # ⚙️ Config Maven Wrapper
├── 📂 .vscode/ # 🔧 Configuración IDE
├── 📄 .gitattributes # 🔍 Reglas Git
├── 📄 .gitignore # 🙈 Archivos ignorados
├── 📄 README.md # 📖 Documentación
├── 📄 mvnw # 🐧 Maven Wrapper (Linux)
├── 📄 mvnw.cmd # � Maven Wrapper (Windows)
└── 📄 pom.xml # 📦 Dependencias Maven
```

---

## 🔍 Solución de Problemas Comunes

### ❌ El backend no se inicia

- Verifica que PostgreSQL esté corriendo: `sudo service postgresql status`
- Revisa los logs de Spring Boot: `mvn spring-boot:run` muestra errores de conexión

### ❌ El frontend no conecta con el backend

- Asegúrate que CORS está habilitado en Spring Boot:

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("http://localhost:5500");
        }
    };
}
```

### ❌ Live Server no funciona

- Instálalo desde VSCode Extensions (ID: ritwickdey.LiveServer)
- Alternativa: Usar `python3 -m http.server 8000` en la carpeta frontend
- 

## 🚨 Estado del Ejercicio

**Sin Culminar**  
*(En desarrollo activo)*

---

## 👥 Autores

<div style="display: flex; gap: 20px; align-items: center; margin-top: 15px;">
  <img src="https://avatars.githubusercontent.com/u/123456789?v=4" width="60" style="border-radius: 50%;">
  <div>
    <h4>Jhorman Jesús Castellanos Morales</h4>
    <p>Desarrollador Backend</p>
    <a href="https://github.com/Jhormancastella" target="_blank">@Jhormancastella</a>
  </div>
</div>


<div style="display: flex; gap: 20px; align-items: center; margin-top: 15px;">
  <img src="https://avatars.githubusercontent.com/u/123456789?v=4" width="60" style="border-radius: 50%;">
  <div>
    <h4>Luis Talero</h4>
    <p>Desarrollador Frontend</p>
        <a href="https://github.com/luistalero" target="_blank">@luistalero</a>
  </div>
</div>


<div style="display: flex; gap: 20px; align-items: center; margin-top: 15px;">
  <img src="https://avatars.githubusercontent.com/u/123456789?v=4" width="60" style="border-radius: 50%;">
  <div>
    <h4>Liseth Arévalo</h4>
    <p>Diseñadora de Base de Datos</p>
        <a href="https://github.com/lisethflorez" target="_blank">@lisethflorez</a>
  </div>
</div>

