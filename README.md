# Blogging Application REST APIs

Welcome to the Blogging Application REST APIs project! This project provides a set of RESTful web services for managing a blogging platform. It is built using Spring Boot, making it easy to set up and run.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

This project is a REST API backend for a blogging application. It allows users to perform various operations related to blog management, such as creating, reading, updating, and deleting blog posts and comments. The application also includes user authentication and authorization using Spring Security.

## Features

- User authentication and authorization with Spring Security
- CRUD operations for blog posts
- CRUD operations for comments
- CRUD operations for users
- Pagination and sorting for blog posts
- Search functionality for blog posts
- JSON data format for API responses and requests
- Exception handling

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 11 or higher
- Maven 3.6.3 or higher
- MySQL database (or any other relational database)

## Installation

To install and run this project, follow these steps:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/RanitMandal/Blogging_Application_REST_APIs.git
    cd Blogging_Application_REST_APIs
    ```

2. **Configure the database:**

    Update the `application.properties` file in the `src/main/resources` directory with your database configuration.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build and run the application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access the API at `http://localhost:8080/api`.

Use tools like Postman or curl to interact with the API.

## API Documentation

The API endpoints and their functionalities are documented using Swagger. Once the application is running, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

### User APIs

Here are some of the key user-related endpoints:

- **Register a new user:**

    ```http
    POST /api/users/register
    Content-Type: application/json

    {
        "username": "newuser",
        "password": "password123",
        "email": "newuser@example.com"
    }
    ```

- **Login a user:**

    ```http
    POST /api/users/login
    Content-Type: application/json

    {
        "username": "newuser",
        "password": "password123"
    }
    ```

- **Get user details:**

    ```http
    GET /api/users/{userId}
    ```

- **Update user details:**

    ```http
    PUT /api/users/{userId}
    Content-Type: application/json

    {
        "username": "updateduser",
        "email": "updateduser@example.com"
    }
    ```

- **Delete a user:**

    ```http
    DELETE /api/users/{userId}
    ```

### JSON Data Format

The APIs accept and return data in JSON format. Here is an example of a blog post JSON object:

```json
{
    "id": 1,
    "title": "First Blog Post",
    "content": "This is the content of the first blog post.",
    "author": {
        "id": 1,
        "username": "author1",
        "email": "author1@example.com"
    },
    "comments": [
        {
            "id": 1,
            "content": "This is a comment.",
            "author": {
                "id": 2,
                "username": "commenter1",
                "email": "commenter1@example.com"
            }
        }
    ]
}
```

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/your-feature`).
6. Create a pull request.

## License

This project is licensed under the License. See the [LICENSE](#) file for details.

## Contact

If you have any questions or suggestions, feel free to contact the project maintainer:

- Name: Ranit Mandal
- Email: [ranitmandal17@gmail.com](ranitmandal17@gmail.com)

---

Thank you for using the Blogging Application REST APIs! Happy coding!
