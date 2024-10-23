# AcKeyServer

**AcKeyServer** is the server-side component for the AcKey mobile app, providing a RESTful API for user authentication, authorization, and database access. It is built with Java using Spring Boot and leverages MongoDB as the database. The API supports secure communication with the AcKey mobile client.

## Features

- **REST API**: Provides endpoints for user registration, authentication, user management and data access.
- **Spring Security**: Implements authentication and authorization mechanisms.
- **MongoDB**: Uses MongoDB for database storage with Spring Data as ORM framework.
- **JWT-Based Authentication**: Secure user login via JWT tokens, which are used for all API communications with the client.
- **Integration with AcKey App**: Designed to work with the [AcKey](https://github.com/YOUR_GITHUB_USERNAME/AcKey) mobile client.

## Tech Stack

- **Java**
- **Spring Boot**: For building the server and APIs.
- **Spring Security**: For managing authentication and authorization.
- **Spring Web**: For creating RESTful APIs.
- **Spring Data MongoDB**: For database interaction with MongoDB.
- **MongoDB**: NoSQL database for storing user and app data.

## How It Works

1. **User Registration & Login**: The API provides endpoints to register new users and log in. During registration, user credentials are securely stored in MongoDB.
2. **JWT Token Management**: After logging in, the server issues a JWT token to the client, which is used for secure API communication. The token is validated by Spring Security for every request.
3. **Authorization**: Certain API endpoints are protected and can only be accessed by authorized users with valid JWT tokens.
4. **Database Access**: Spring Data MongoDB is used to interact with MongoDB for storing and retrieving user data and other information required by the client.
