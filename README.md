# Storefront-App

Storefront Backend App project

## Getting Started

### To run the application, you must follow the following instructions

#### 1- Create an `application.properties` file under `/src/main/resources` to store environment variables required to run this application

Here's an example of how you should configure your `application.properties` file:

```
spring.datasource.password=admin
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

#
# Spring Data REST properties
#
server.port=3000
server.servlet.context-path=/api

# App Properties
storefront.app.jwtSecret=[secret key]
storefront.app.saltRounds=10
storefront.app.jwtExpirationMs= 86400000
spring.mvc.dispatch-options-request=true
```

#### 2- Set up the database driver and connect it to the application

- Install PostgreSQL locally, manually set up a user account, and manually create `storefront` database.

#### 3- In the project directory, run the command `mvnw spring-boot:run` to run the application
