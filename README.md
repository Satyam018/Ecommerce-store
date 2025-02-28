
# E-commerce Store Microservices Backend Application


This is a backend REST API for e-commerce store. 


## Tech Stack

**Languages & Frameworks:**  
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) &nbsp;&nbsp;&nbsp;  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white) &nbsp;&nbsp;&nbsp;  
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  

**Service Communication:**  
![OpenFeign](https://img.shields.io/badge/OpenFeign-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  

**Security:**  
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white) &nbsp;&nbsp;&nbsp;  
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)  

**Discovery Server:**  
![Netflix Eureka](https://img.shields.io/badge/Netflix%20Eureka-0078D7?style=for-the-badge&logo=netflix&logoColor=white)  

**API Gateway:**  
![Spring Cloud Gateway](https://img.shields.io/badge/Spring%20Cloud%20Gateway-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  

**Databases:**  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)  

**Build:**  
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)  

**Containerization:**  
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)  

## Solution Architecture

The architecture includes several services connected to their own database. It  has an API gateway which is the entry point for the users. All of them are registered to the Eureka server registry. 

![Image](https://github.com/user-attachments/assets/8d496aa8-58fb-4911-aa1d-80de1a4f80f7)


The API gateway sends the request to the different services based on the request made by the users.

![Image](https://github.com/user-attachments/assets/82d0a74f-788d-4bb6-abe6-45c85685e49a)


The Communication between services takes place with the help Open Feign Library. I have used the Coreography SAGA Pattern to ensure data consistency.


![Image](https://github.com/user-attachments/assets/04df9e36-4f90-429b-bfd4-8361c051669a)

## Services

- **Auth Services :** Manages user registration and authentication. Provides endpoints for registering new users and generating JWT tokens upon successful authentication. It integrates with the AuthService to handle user creation and token generation securely

- **Product Services :** A RESTful API for managing products, including CRUD operations, stock management, and order processing.

-  **Customer Services :** Handles customer and address management with features like retrieval, creation, updates, and validation through a RESTful API.

-  **Cart Services :** Manages customer carts and cart items, allowing retrieval, creation, updates, and deletion to ensure a smooth shopping experience through a RESTful API.

- **Order Services :** Handles order and order item management with features like retrieval, creation, updates, and deletion. Implements the Saga pattern to ensure transactional consistency across services.

- **Payment Services :** Handles payment processing and retrieval of payment details based on order ID. Ensures secure and reliable transactions.


## Other Components

- **Eureka Server Registry :** Acts as a service discovery server that registers and manages all microservices. It enables dynamic service discovery, allowing clients to locate and interact with available services in the ecosystem.

- **API Gateway :** Routes requests to appropriate services, applying security filters such as authentication and authorization. Ensures seamless communication between microservices by managing request routing and enforcing security protocols


## Getting Started

1. **Clone the Repository**  
   Begin by cloning the repository to your local machine:

   ```bash
   git clone <repository_url>
   cd <repository_name> 

2. **Create Build Files for All Services**
Each service requires a build file (e.g., Dockerfile, build configuration). Ensure you have the necessary build files set up for each service in your project.

3. **Navigate to the e-commerce-store Directory**
Once you've prepared the build files, navigate to the e-commerce-store directory:

    
        cd Ecommerce-store


4. **Run Docker Compose**
To build and start all services, run the following Docker Compose command:

        docker-compose up --build



## Accessible Endpoints



### **Auth Services API Endpoints**

- **POST** `/auth/register`  
  _Register a new user by providing user details (email, password)._

- **POST** `/auth/token`  
  _Authenticate a user and generate a JWT token upon successful authentication with provided credentials (email, password)._

- **GET** `/auth/validate` (Commented out in code)  
  _Validate the provided JWT token (if enabled)._  


---


### **Product Services API Endpoints**

- **GET** `/products`  
  _Retrieve a list of all products._

- **GET** `/products/hasproduct/{id}`  
  _Check if a product exists by its ID._

- **GET** `/products/{id}`  
  _Retrieve a specific product by its ID._

- **POST** `/products/reduceitemorder`  
  _Reduce the quantity of items ordered._

- **POST** `/products/restoreStock`  
  _Restore the stock for a product._

- **POST** `/products/add`  
  _Add a new product to the catalog._

- **DELETE** `/products/delete/{id}`  
  _Delete a product by its ID._

- **POST** `/products/update/{id}`  
  _Update the details of a product by its ID._

---

### **Customer Services API Endpoints**

- **GET** `/customers`  
  _Retrieve a list of all customers._

- **GET** `/customers/{id}`  
  _Retrieve a specific customer by its ID._

- **GET** `/customers/hascustomer/{id}`  
  _Check if a customer exists by their ID._

- **POST** `/customers/add`  
  _Add a new customer._

- **POST** `/customers/update/{id}`  
  _Update the details of a customer by their ID._


- **POST** `/address/add`  
  _Add a new address._

- **GET** `/address/{customerid}`  
  _Retrieve all addresses associated with a specific customer._

- **DELETE** `/address/deleteaddress`  
  _Delete a customer's address._

- **POST** `/address/updateaddress`  
  _Update an address for a customer._

---

### **Cart Services API Endpoints**

- **GET** `/cart`  
  _Retrieve all cart details._

- **GET** `/cart/allcarts`  
  _Retrieve all carts._

- **GET** `/cart/customer/{customerid}`  
  _Retrieve the cart associated with a specific customer._

- **GET** `/cart/cartdetails/{customerId}`  
  _Retrieve detailed cart info by customer ID._

- **GET** `/cart/cartitem/{id}`  
  _Retrieve a specific cart item by its ID._

- **POST** `/cart/addcart/{customerid}`  
  _Create a new cart for a customer._

- **POST** `/cart/addcartitem`  
  _Add a new item to the cart._

- **DELETE** `/cart/clearcart/{customerId}`  
  _Clear all items in a customer's cart._

- **DELETE** `/cart/deleteitem/{cartItemId}`  
  _Delete a specific item from the cart._

- **POST** `/cart/updatecartitem`  
  _Update the details of a cart item._

---

### **Order Services API Endpoints**

- **GET** `/order/orders`  
  _Retrieve a list of all orders._

- **GET** `/order`  
  _Retrieve detailed information of all orders._

- **GET** `/order/order/{id}`  
  _Retrieve a specific order by its ID._

- **POST** `/order/add`  
  _Add a new order._

- **DELETE** `/order/deleteOrder/{orderId}`  
  _Delete a specific order by its ID._

- **DELETE** `/order/deleteOrderItem/{orderItemId}`  
  _Remove a specific item from an order._

- **PUT** `/order/updatestatus/delivered/{orderId}`  
  _Change the status of an order to "delivered."_

- **PUT** `/order/updatequantity`  
  _Update the quantity of an item in an order._

---

### **Payment Services API Endpoints**

- **POST** `/payment/makepayment`  
  _Process a payment._

- **POST** `/payment/getpaymentinfo/{orderid}`  
  _Retrieve payment details for a specific order by its ID._
