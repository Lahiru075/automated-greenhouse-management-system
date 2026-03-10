# Automated Greenhouse Management System (AGMS)

AGMS is a cloud-native, microservices-based platform designed for modern precision agriculture. The system automates climate control by fetching real-time telemetry from external IoT sensors and applying custom business rules to maintain ideal growing conditions.

## 🚀 System Architecture
The system is built using the Spring Cloud ecosystem and follows a distributed microservices architecture:
- **Infrastructure Services:** Service Discovery, Centralized Configuration, and API Gateway.
- **Domain Microservices:** Auth Service, Zone Management Service, Sensor Telemetry Service, Automation Service, and Crop Inventory Service.

---

## 🛠️ Prerequisites
- **Java:** Version 21
- **Build Tool:** Maven
- **Database:** MySQL (Ensure all databases are created as per configuration)
- **Centralized Config Repository:** [https://github.com/Lahiru075/agms-config-repo](https://github.com/Lahiru075/agms-config-repo.git)

---

## 🏁 Startup Instructions (Step-by-Step)

To ensure the system functions correctly, the services **must** be started in the following specific order:

### **Step 1: Infrastructure Services (Start First)**

1.  **Service Registry (Eureka Server)**
    - **Folder:** `serviceregistry`
    - **Port:** `8761`
    - Wait for the dashboard to be available at `http://localhost:8761`.

2.  **Config Server**
    - **Folder:** `configserver`
    - **Port:** `8888`
    - **Note:** This service fetches configurations from the Git repo mentioned above. Ensure it is UP before proceeding.

3.  **API Gateway**
    - **Folder:** `api-gateway`
    - **Port:** `8080`
    - All external client requests will pass through this single entry point.

### **Step 2: Domain Microservices (Start Second)**

Once the infrastructure is UP and running, start the following services:
- **Auth/Identity Service** (Port: `8085`) - User registration and JWT generation.
- **Zone Management Service** (Port: `8081`) - Greenhouse zone and device registration logic.
- **Sensor Telemetry Service** (Port: `8082`) - Acts as a data bridge to fetch telemetry from External IoT API.
- **Automation Service** (Port: `8083`) - Rule engine that processes sensor data and triggers actions.
- **Crop Inventory Service** (Port: `8084`) - Manages crop batches and their growth lifecycle stages.

---

## 🔒 Security
- **Internal Security:** JWT (JSON Web Token) authorization is implemented at the Gateway level to protect all internal APIs.
- **External Security:** The Sensor Service manages authentication with the external IoT provider to obtain secure telemetry data.

---

## 🧪 Testing with Postman
A Postman Collection containing all API endpoints is included in the project root:
- **File:** `AGMS_Postman_Collection.json`
- **Steps:** 
    1. Import the collection into Postman.
    2. Register and Login to get the **Bearer Token**.
    3. Use the token in the `Authorization` header for all protected requests.

---

## 📊 Monitoring
All active microservices can be monitored via the Eureka Dashboard:
- **URL:** [http://localhost:8761](http://localhost:8761)
- Refer to `docs/eureka-dashboard.png` for a visual reference of a healthy system status.
