# 🛒 E-Commerce Microservices Application

A scalable, production-ready e-commerce platform built using microservices architecture with Spring Boot and Spring Cloud ecosystem.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Microservices](#microservices)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [CI/CD](#cicd)
- [Contributing](#contributing)
- [Contact](#contact)

## 🎯 Overview

This project demonstrates a complete e-commerce solution built with microservices architecture, featuring user authentication, product catalog management, shopping cart functionality, and order processing. The system uses event-driven communication between services and follows industry best practices for scalability and maintainability.

## 🏗️ Architecture

The application follows a microservices architecture pattern with the following components:

```
┌─────────────┐
│   Clients   │
└──────┬──────┘
       │
┌──────▼──────────────┐
│   API Gateway       │
│  (Spring Cloud)     │
└──────┬──────────────┘
       │
┌──────▼──────────────┐
│ Service Discovery   │
│  (Eureka Server)    │
└──────┬──────────────┘
       │
   ────┴────────────────────────────
   │           │           │        │
┌──▼──┐   ┌───▼───┐  ┌───▼───┐ ┌──▼────┐
│Auth │   │Product│  │ Cart  │ │ Order │
│ Svc │   │  Svc  │  │  Svc  │ │  Svc  │
└─────┘   └───────┘  └───────┘ └───────┘
```

## ✨ Features

- **User Authentication & Authorization**: Secure JWT-based authentication system
- **Product Management**: Complete CRUD operations for product catalog
- **Shopping Cart**: Real-time cart management with add, update, and remove operations
- **Order Processing**: End-to-end order creation and management
- **Event-Driven Communication**: Asynchronous messaging between microservices
- **Service Discovery**: Automatic service registration and discovery using Eureka
- **API Gateway**: Centralized routing and load balancing
- **Comprehensive Testing**: 85%+ unit test coverage with JUnit and Mockito
- **API Documentation**: Interactive API docs with Swagger/OpenAPI
- **CI/CD Pipeline**: Automated build, test, and deployment workflows

## 🛠️ Tech Stack

### Backend
- **Java 8**: Core programming language
- **Spring Boot**: Application framework
- **Spring Cloud**: Microservices infrastructure
  - Eureka Server: Service discovery
  - API Gateway: Routing and load balancing
  - Config Server: Centralized configuration (optional)
- **PostgreSQL**: Primary database
- **Spring Data JPA**: Data persistence layer
- **Spring Security**: Authentication and authorization

### DevOps & Tools
- **Docker**: Containerization
- **GitHub Actions**: CI/CD pipeline
- **Swagger/OpenAPI**: API documentation
- **Postman**: API testing
- **Git**: Version control

### Testing
- **JUnit 5**: Unit testing framework
- **Mockito**: Mocking framework
- **Spring Boot Test**: Integration testing

## 🔧 Microservices

### 1. Authentication Service
- User registration and login
- JWT token generation and validation
- Password encryption and security

### 2. Product Service
- Product CRUD operations
- Category management
- Inventory tracking

### 3. Cart Service
- Add/remove items from cart
- Update item quantities
- Calculate cart totals

### 4. Order Service
- Order creation and processing
- Order history tracking
- Order status management

### 5. Eureka Server
- Service registration
- Service discovery
- Health monitoring

### 6. API Gateway
- Request routing
- Load balancing
- Authentication filtering

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6+
- PostgreSQL 12+
- Docker (optional)
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ecommerce-microservices.git
   cd ecommerce-microservices
   ```

2. **Set up PostgreSQL Database**
   ```sql
   CREATE DATABASE ecommerce_auth;
   CREATE DATABASE ecommerce_products;
   CREATE DATABASE ecommerce_cart;
   CREATE DATABASE ecommerce_orders;
   ```

3. **Configure application properties**
   
   Update `application.properties` or `application.yml` in each service with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Start services in order**
   ```bash
   # Start Eureka Server first
   cd eureka-server
   mvn spring-boot:run
   
   # Start API Gateway
   cd ../api-gateway
   mvn spring-boot:run
   
   # Start microservices
   cd ../auth-service
   mvn spring-boot:run
   
   cd ../product-service
   mvn spring-boot:run
   
   cd ../cart-service
   mvn spring-boot:run
   
   cd ../order-service
   mvn spring-boot:run
   ```

### Using Docker (Alternative)

```bash
# Build and run all services
docker-compose up --build
```

## 📚 API Documentation

Once the services are running, access Swagger UI for interactive API documentation:

- **Authentication Service**: http://localhost:8081/swagger-ui.html
- **Product Service**: http://localhost:8082/swagger-ui.html
- **Cart Service**: http://localhost:8083/swagger-ui.html
- **Order Service**: http://localhost:8084/swagger-ui.html
- **API Gateway**: http://localhost:8080/swagger-ui.html

### Sample API Endpoints

#### Authentication
```
POST /api/auth/register - Register new user
POST /api/auth/login - User login
```

#### Products
```
GET /api/products - Get all products
GET /api/products/{id} - Get product by ID
POST /api/products - Create new product
PUT /api/products/{id} - Update product
DELETE /api/products/{id} - Delete product
```

#### Cart
```
GET /api/cart - Get user cart
POST /api/cart/items - Add item to cart
PUT /api/cart/items/{id} - Update cart item
DELETE /api/cart/items/{id} - Remove item from cart
```

#### Orders
```
GET /api/orders - Get user orders
POST /api/orders - Create new order
GET /api/orders/{id} - Get order by ID
```

## 🧪 Testing

Run unit tests for all services:

```bash
mvn test
```

Run tests with coverage report:

```bash
mvn clean test jacoco:report
```

Coverage reports are generated in `target/site/jacoco/index.html` for each service.

**Current Test Coverage**: 85%+

## 🔄 CI/CD

The project uses GitHub Actions for continuous integration and deployment:

- **Build**: Automated Maven build on every push
- **Test**: Runs all unit tests and generates coverage reports
- **Docker**: Builds and pushes Docker images
- **Deploy**: Automated deployment pipeline (configure as needed)

View the workflow file: `.github/workflows/ci-cd.yml`

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

**Divya Ojha**

- Email: divyaojha792@gmail.com
- LinkedIn: https://www.linkedin.com/in/thedivyaojha/
- Portfolio: https://thedivyaojha.github.io/divya-portfolio/
- GitHub: @thedivyaojha

---

⭐ Star this repository if you find it helpful!

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
