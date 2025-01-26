# Inditex Pricing

***Repository for Inditex task made by Enrique Barca***

[![es](https://img.shields.io/badge/lang-Español-blue.svg)](./utils/README.es.md)

## 1. How to run the application and tests.

### Run it without using the docker image:

- Once we have the code in our IDE, we have to configure our ***run configuration, what kind of app it is, which file is
the main class, etc***. Then we should be able to run the application, ***the DB will run in the port 3000*** keep that in mind.

#### Running the tests

- We can execute the command below from the root application folder to run all the tests in the app.

```sh

    mvn clean install

```
### Run it with docker image

- We can build the image from the docker-compose file from the root of the application folder ***Make sure your docker is
up, or you may get an error***
 

- We can build the image, and run it when it finishes, with all the services needed with the command below:

```sh

    docker-compose up --build

```

## 2. H2 database:

- The application is running in an in-memory database, as it was suggested in the requirements file, so the URL to visualize the console
  would be:

```

    http://localhost:3000/h2-console

```

- There is no need to add any data to the DB as the ***[script](src/main/resources/data.sql)*** will be executed and add the 
information needed (Hibernate will create the table).

- And, just in case, the values in the console itself should be:


JDBC URL:
```
jdbc:h2:file:./src/main/resources/db/InditexPricingTaskDb
```
```
  User Name: Enrique
  Password: inditexTask
```

## 3. Postman collection:

- In this git repository, we should be able to see, inside the utils folder in the project root, a JSON file called
  ```Inditex.postman_collection.json```. It contains all the endpoint information, the security credentials and the tests
with the values, from the requirements file, already filled.

We only have to open Postman and import the file ```"Collections" tab > "Import" button```


## 4. Endpoints - Documentation:

- The application has a Swagger dependency so, when the program is running, we can visit the url below for reference if needed

```
    http://localhost:3000/swagger-ui/index.html
```

- #### Now a quick introduction to the endpoint.

  | Name          | URL                 | What does                                                  | Constrains                                                                                                                                     |
  |---------------|---------------------|------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
  | Price request | /v1/price   | Get the price for a product and brand for a specific date. | If there is no match will throw a custom "PriceNotFoundException" exception. And if the "applicationDate" has't the right format will throw a "DateTimeFormatException" |

***Of course the endpoint has other edge cases in case the information is missing, the IDs has wrong format or the parameters are not send***

## Problem Description
- The objective of this project is to build a Spring Boot application that exposes a REST endpoint to provide
pricing details for products based on the following inputs:

#### Input Parameters
1. Application Date: The date and time for which the pricing needs to be checked.
2. Product ID: The unique identifier of the product.
3. Brand ID: The identifier of the brand or store chain.

#### Expected Output

The endpoint should return:

1. Product ID: The unique identifier of the product.
2. Brand ID: The identifier of the store chain.
3. Rate to Apply (Tariff): The applicable rate for the product.
4. Application Dates: The start and end dates when the price is valid.
5. Final Price: The calculated price to apply.

#### Solution Overview

- This Spring Boot application processes the input parameters and determines the appropriate price for a given product at a specific date and store chain. The application considers multiple price lists and selects the one with:

- The highest priority.
- An active date range that matches the input date.

#### Use Case Example
 
- If multiple prices exist for the same product and brand, the API selects the one with:

1. The highest priority.
2. A validity period that includes the provided date.

### 5.1 What did I do?

- This is a small example of some of my skills developing a ***hexagonal architecture application DDD based***.
- I add a basic *security* configuration as we can see in the [security class,](src/main/java/com/inditex/pricing/infrastructure/config/security/SecurityConfig.java)
and we can test going to our postman once we have the collection exported, going to the "Authorization" tab.
- I create the ***[Dockerfile](./Dockerfile)*** and the ***[docker-compose](./docker-compose.yml)*** for a easier way to
build and run the application and the h2 DB instance.
- I create custom exceptions such us ***[DateTimeFormatException](src/main/java/com/inditex/pricing/infrastructure/exception/DateTimeFormatException.java)***
and ***[PriceNotFoundException](src/main/java/com/inditex/pricing/infrastructure/exception/PriceNotFoundException.java)***, for a 
better back-end communication, without forgetting the global exception handling ***[GlobalExceptionHandler](src/main/java/com/inditex/pricing/infrastructure/exception/GlobalExceptionHandler.java)***
where we can see the method created to handle most of the general exceptions and all the custom that we may create in the future.

## 6. Patterns used:

### Hexagonal Architecture (Ports and Adapters)
- Role: The application is structured to separate core business logic (domain) from external systems (like databases and REST APIs).

- Components:
Domain Layer: Contains entities, value objects, and domain services.
Application Layer (Ports): Defines use cases and interacts with external systems through interfaces.
Infrastructure Layer (Adapters): Implements the interfaces to connect to external systems.

### Repository Pattern
   - Role: Abstracts data access logic. The PriceRepository (a JPA repository) interacts with the database while the domain
remains agnostic to persistence details.
   - Implementation:
   PriceRepository in the infrastructure layer implements the repository pattern by extending JpaRepository.

### Singleton Pattern
- Role: Ensures a single instance of shared resources such as configuration or logging.
- Examples:
The ***[Logger instance](src/main/java/com/inditex/pricing/infrastructure/LoggerConfig.java)*** in ***[GetPriceUseCaseAdapter](src/main/java/com/inditex/pricing/application/service/GetPriceUseCaseAdapter.java)***
or other components.
Beans defined in the Spring context are singletons by default unless otherwise specified.

### Template Method Pattern
- Role: Defines the skeleton of an algorithm in a base class and lets subclasses override specific steps.
- Example:
Such as what I did with the exception father class ***[InditexPricingException](src/main/java/com/inditex/pricing/infrastructure/exception/InditexPricingException.java)***.

### Dependency Injection (DI) Pattern
- Role: Promotes loose coupling by injecting dependencies into components.
- Example:
Spring Boot automatically injects dependencies like PriceRepository into services.

### DTO (Data Transfer Object) Pattern
- Role: Facilitates the transfer of data between layers.
- Examples:
***[PriceRequest](src/main/java/com/inditex/pricing/infrastructure/web/request/PriceRequest.java)*** and ***[PriceResponse](src/main/java/com/inditex/pricing/infrastructure/web/response/PriceResponse.java)***
act as DTOs to pass data between the controller, service, and client.

### Decorator Pattern
- Role: Adds responsibilities to objects dynamically.
- Example:
Middleware-like components in the application layer for logging or security.

## Testing

#### Run Tests

To execute unit and integration tests:

```bash
mvn test
```
Code Coverage
The project integrates with JaCoCo for code coverage. After running the tests, the coverage report is available at:

```bash
target/site/jacoco/index.html
```