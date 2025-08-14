# E-Commerce Microservices Platform

A comprehensive e-commerce platform built with Spring Boot microservices architecture, featuring distributed services for customer management, product catalog, order processing, payment handling, and notifications.

## 🏗️ Architecture

This project follows a microservices architecture pattern with the following components:

### Core Services
- **Config Server** - Centralized configuration management
- **Discovery Service** - Service registration and discovery using Eureka
- **API Gateway** - Single entry point with routing and security
- **Customer Service** - Customer management and profiles
- **Product Service** - Product catalog and inventory
- **Order Service** - Order processing and management
- **Payment Service** - Payment processing and validation
- **Notification Service** - Email notifications and messaging

### Infrastructure
- **PostgreSQL** - Primary database for most services
- **Apache Kafka** - Event-driven communication between services
- **Zipkin** - Distributed tracing and monitoring
- **Keycloak** - Authentication and authorization
- **Docker** - Containerization support

## ✨ Features

### Customer Management
- Customer registration and profile management
- Address management with embedded entities
- Customer validation and error handling
- RESTful API for customer operations

### Product Catalog
- Product management with categories
- Inventory tracking and availability
- Bulk product purchasing with stock validation
- Database migrations with Flyway
- Pre-populated sample data

### Order Processing
- Complete order lifecycle management
- Order line items tracking
- Integration with customer and product services
- Payment processing integration
- Event-driven notifications

### Payment System
- Multiple payment method support (PayPal, Credit Card, Visa, etc.)
- Payment validation and processing
- Audit trail with creation/modification timestamps
- Kafka-based payment confirmations

### Notification System
- Email notifications for order and payment confirmations
- Thymeleaf-based HTML email templates
- Asynchronous email processing
- Kafka event consumption

### Security & Monitoring
- OAuth2 resource server with JWT tokens
- Keycloak integration for authentication
- Distributed tracing with Zipkin
- Centralized logging and monitoring

## 🛠️ Technologies

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.5+** - Application framework
- **Spring Cloud** - Microservices infrastructure
- **Spring Security** - Security framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM framework
- **PostgreSQL** - Relational database
- **Apache Kafka** - Message streaming
- **Eureka** - Service discovery
- **OpenFeign** - Service-to-service communication

### Infrastructure & Tools
- **Docker & Docker Compose** - Containerization
- **Zipkin** - Distributed tracing
- **Keycloak** - Identity and access management
- **Flyway** - Database migrations
- **Maven** - Build automation
- **Thymeleaf** - Template engine for emails

## 🚀 Quick Start

### Prerequisites
- Java 17 or later
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL (or use Docker)

### 1. Infrastructure Setup

Start the required infrastructure services:

```bash
docker-compose up -d postgres pgadmin kafka zipkin keycloak mail-dev
```

This will start:
- PostgreSQL database on port 5432
- pgAdmin on port 5050
- Apache Kafka on port 9092
- Zipkin on port 9411
- Keycloak on port 9098
- MailDev on port 1080

### 2. Database Setup

Create the required databases in PostgreSQL:
```sql
CREATE DATABASE customer;
CREATE DATABASE product;
CREATE DATABASE "order";
CREATE DATABASE notification;
```

### 3. Service Startup Order

Start the services in the following order:

**1. Config Server**
```bash
cd config-server
./mvnw spring-boot:run
```

**2. Discovery Service**
```bash
cd discovery
./mvnw spring-boot:run
```

**3. API Gateway**
```bash
cd gateway
./mvnw spring-boot:run
```

**4. Core Services** (can be started in parallel)
```bash
# Customer Service
cd customer
./mvnw spring-boot:run

# Product Service
cd product
./mvnw spring-boot:run

# Order Service
cd order
./mvnw spring-boot:run

# Payment Service
cd payment
./mvnw spring-boot:run

# Notification Service
cd notification
./mvnw spring-boot:run
```

## 📡 API Endpoints

### Customer Service (Port 8090)
```
POST   /api/v1/customers          - Create customer
PUT    /api/v1/customers          - Update customer
GET    /api/v1/customers          - Get all customers
GET    /api/v1/customers/{id}     - Get customer by ID
DELETE /api/v1/customers/{id}     - Delete customer
GET    /api/v1/customers/exists/{id} - Check if customer exists
```

### Product Service (Port 8050)
```
POST   /api/v1/products           - Create product
GET    /api/v1/products           - Get all products
GET    /api/v1/products/{id}      - Get product by ID
POST   /api/v1/products/purchase  - Purchase products (bulk)
```

### Order Service (Port 8070)
```
POST   /api/v1/orders             - Create order
GET    /api/v1/orders             - Get all orders
GET    /api/v1/orders/{id}        - Get order by ID
GET    /api/v1/order-lines/order/{id} - Get order lines by order ID
```

### Payment Service (Port 8060)
```
POST   /api/v1/payments           - Create payment
```

### API Gateway (Port 8222)
All requests should go through the API Gateway which routes to appropriate services.

## 🔧 Configuration

### Service Ports
- Config Server: 8888
- Discovery Service: 8761
- API Gateway: 8222
- Customer Service: 8090
- Product Service: 8050
- Order Service: 8070
- Payment Service: 8060
- Notification Service: 8040

### Database Configuration
Each service connects to PostgreSQL with the following pattern:
- URL: `jdbc:postgresql://localhost:5432/{service_name}`
- Username: `postgres`
- Password: `safwene17`

### Kafka Topics
- `order-topic` - Order confirmations
- `payment-topic` - Payment confirmations

## 📧 Email Templates

The notification service includes HTML email templates:
- **Order Confirmation** - Detailed order summary with products
- **Payment Confirmation** - Payment success notification

Templates are located in `notification/src/main/resources/templates/`

## 🐳 Docker Support

The project includes a comprehensive Docker Compose configuration for all infrastructure services:

```bash
# Start all infrastructure
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

## 📊 Monitoring & Tracing

- **Eureka Dashboard**: http://localhost:8761
- **Zipkin Tracing**: http://localhost:9411
- **Keycloak Admin**: http://localhost:9098 (admin/admin)
- **MailDev**: http://localhost:1080
- **pgAdmin**: http://localhost:5050

## 🔐 Security

The platform implements OAuth2 with JWT tokens:
- Keycloak provides authentication services
- API Gateway handles authorization
- Services are secured with Spring Security
- JWT tokens are validated at the gateway level

## 🏃‍♂️ Development

### Building All Services
```bash
# Build all services
find . -name "pom.xml" -execdir mvn clean compile \;

# Run tests
find . -name "pom.xml" -execdir mvn test \;
```

### Service Communication
Services communicate using:
- **Synchronous**: OpenFeign clients for direct service calls
- **Asynchronous**: Kafka messages for event-driven communication

## 📝 Sample Data

The product service includes Flyway migrations with sample data:
- 5 product categories (Keyboards, Monitors, Screens, Mice, Accessories)
- 25 sample products with realistic data
- Proper relationships and constraints

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Netflix OSS for Eureka service discovery
- Apache Kafka for reliable messaging
- PostgreSQL for robust data storage
