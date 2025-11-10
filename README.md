# 🛰️ Satellite Ingestion System Backend

A **cloud-ready, Dockerized Spring Boot backend** that ingests and processes satellite metadata through a **RabbitMQ message broker**, persists the data in **PostgreSQL/PostGIS**, and exposes a modular service architecture for spatial analytics and downstream consumers.

---

## 🚀 Overview

This project simulates a **real-time satellite data ingestion pipeline**.  
The system is built with **Spring Boot** and designed for **containerized deployment** via Docker Compose.

### Key Features
- **Microservice-style architecture** with clear separation between API, worker, and message broker.
- **RabbitMQ-driven ingestion pipeline**: publisher emits satellite metadata events; worker subscribes and processes them.
- **PostGIS integration** for storing and querying geospatial metadata.
- **Scalable and fault-tolerant design**, leveraging asynchronous communication.
- **Dockerized services** (PostgreSQL + PostGIS, RabbitMQ, Spring Boot app) for reproducible deployment.

---

## 🧩 System Architecture
            +----------------------+
            |   Satellite API      |
            |  (Spring Boot App)   |
            +----------+-----------+
                       |
                       | Publish satellite data events
                       v
            +----------+-----------+
            |       RabbitMQ       |
            +----------+-----------+
                       |
                       | Subscribed worker queue
                       v
            +----------+-----------+
            |     Worker Service   |
            | (Spring Boot Worker) |
            +----------+-----------+
                       |
                       | Persist metadata
                       v
            +----------+-----------+
            |   PostgreSQL +       |
            |     PostGIS DB       |
            +----------------------+


---

## 🛠️ Tech Stack

| Component | Technology |
|------------|-------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.x (Spring Web, Spring AMQP, Spring Data JPA) |
| **Database** | PostgreSQL + PostGIS extension |
| **Message Broker** | RabbitMQ |
| **Containerization** | Docker & Docker Compose |
| **Build Tool** | Maven |
| **ORM** | Hibernate (with PostGIS dialect) |
| **Spatial Data Handling** | `org.locationtech.jts`, `hibernate-spatial` |

---

## ⚙️ Setup & Deployment

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/satellite-ingestion-system.git
cd satellite-ingestion-system

2️⃣ Start Services with Docker Compose
docker-compose up --build

This command launches:

Postgres/PostGIS container (postgis:latest)

RabbitMQ broker (rabbitmq:3-management)

Spring Boot API (port 8080)

Worker service (port 8081)

📡 Workflow

Satellite API Service

Receives POST requests containing satellite metadata (timestamp, coordinates, mission ID, etc.)

Publishes messages to a RabbitMQ exchange.

Worker Service

Listens to the RabbitMQ queue.

Parses and validates incoming payloads.

Persists structured data into PostgreSQL/PostGIS.

Database Layer

Stores all ingested events with spatial coordinates (POINT, POLYGON, etc.).

Enables spatial queries, e.g., “Find all satellites over region X.”

🧪 Example API
POST /api/v1/satellite
{
  "satelliteId": "SAT-001",
  "timestamp": "2025-03-10T12:00:00Z",
  "latitude": 34.05,
  "longitude": -118.25,
  "altitudeKm": 705
}
Response:
{ "status": "queued", "message": "Satellite data published to RabbitMQ" }

Example Spatial Query
SELECT satellite_id, ST_AsText(location)
FROM satellite_ingest
WHERE ST_Intersects(
  location,
  ST_GeomFromText('POLYGON((-118.5 33.9, -118.5 34.2, -118.1 34.2, -118.1 33.9, -118.5 33.9))', 4326)
);


