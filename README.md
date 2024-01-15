# Event Notification System

## Overview

This application, developed using Spring Boot and Java 17 with PostgreSQL, is designed to facilitate the sending of email notifications to registered users for various events. The notifications are scheduled and sent using the `@Scheduled` feature provided by Spring Boot. The application relies on three main database tables: `Events`, `Users`, and `Messages`. The relationship between events and users is defined as a One-to-Many association, and messages are stored separately in a dedicated table that references the IDs of both events and users.

## Features

- **Scheduled Notifications**: Utilizes Spring Boot's `@Scheduled` feature to automate the sending of notifications to users based on registered events.

- **Database Schema**:
  - **Events Table**: Stores information about various events.
  - **Users Table**: Manages user details and their association with events.
  - **Messages Table**: Stores messages associated with specific events and users.

- **Data Relationship**:
  - The `Events` and `Users` tables have a One-to-Many relationship, allowing each event to be associated with multiple users.
  - Messages are stored in a separate table, referencing both the event and user IDs.

## Getting Started

### Prerequisites

- Ensure you have Java 17 installed.
- Set up a PostgreSQL database and configure the application.properties file with the appropriate database details.

### Building and Running the Application

1. Clone the repository:

    ```bash
    git clone https://github.com/your/repository.git
    ```

2. Navigate to the project directory:

    ```bash
    cd event-notification-system
    ```

3. Build the application:

    ```bash
    ./mvnw clean install
    ```

4. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

## Configuration

Update the `application.properties` file using a `.env` file inside the resources folder with the necessary database details and any other configuration parameters.

```properties
# DataSource configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# Hibernate configuration
spring.jpa.database=${DATABASE}
spring.jpa.show-sql=${DATABASE_LOB_CONTEXTUAL_CREATION}
spring.jpa.hibernate.ddl-auto=${DATABASE_HIBERNATE_DDL_AUTO}
spring.jpa.properties.hibernate.dialect=${DATABASE_DIALECT}

# Specify the JDBC driver for PostgreSQL
spring.datasource.driver-class-name=${DATABASE_DRIVER_CLASS}

# Gmail SMTP settings
spring.mail.host=${EMAIL_HOST}
spring.mail.port=${EMAIL_PORT}
spring.mail.username=${EMAIL_USERNAME}
spring.mail.password=${EMAIL_PASSWORD}
```

## Usage

1. Add events and users to the respective tables using the provided APIs.
2. Schedule notifications using the `@Scheduled` feature.
3. The application will automatically send notifications to registered users based on the defined schedule.

## API Endpoints

Document any relevant API endpoints for managing events, users, and notifications.

### Events

- **Endpoint 1**: `/events/save` - Save event.
```json
{
  "id":1,
  "name": "Json Party",
  "date": "2024-01-10",
  "message": "The beats goes on!"
}
```
- **Endpoint 2**: `/events/getById/{id}` - Get event by ID.
```json
{
    "id": 252,
    "name": "Json Party",
    "date": "2024-01-15",
    "message": "The beats goes on!",
    "users": [
        {
            "id": 502,
            "name": "Johan",
            "email": "rajevij563@tsderp.com"
        }
    ]
}
```
- **Endpoint 3**: `/events/getAll` - Get all events.
```json
[
    {
        "id": 252,
        "name": "Json Party",
        "date": "2024-01-15",
        "message": "The beats goes on!",
        "users": [
            {
                "id": 502,
                "name": "Johan",
                "email": "rajevij563@tsderp.com"
            }
        ]
    }
]
```
- **Endpoint 4**: `/events/deleteById/{id}` - Delete an event by a given ID.
- **Endpoint 5**: `/events/updateById/{id}` - Update by a given ID.

### Users

- **Endpoint 1**: `/users/save` - Save an user.
```json
{
  "id":1,
  "name": "Teste Reis",
  "email": "teste@gmail.com",
  "event": {"id": 252}
}
```
- **Endpoint 2**: `/users/getById/{id}` - Get user by ID.
```json
{
    "id": 502,
    "name": "Johan",
    "email": "rajevij@.com"
}
```
- **Endpoint 3**: `/users/getAll` - Get all users.
```json
[
    {
        "id": 502,
        "name": "Johan",
        "email": "rajevij@.com"
    }
]
```
- **Endpoint 4**: `/users/deleteById/{id}` - Delete by a given ID.
- **Endpoint 5**: `/users/updateById/{id}` - Update by a given ID.

### Notifications

- **Endpoint 1**: `/notifications/send` - Send all pending notifications.

## Contributors

- List the contributors or acknowledge external libraries used in the project.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Any additional acknowledgments or credits can be added here.
