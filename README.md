# Fitness Lecture Booking System

## Overview

This is a Spring Boot application for managing and booking fitness classes. It includes features for creating classes, booking participation, and managing users.

## Key Features

- **Class Management**: Create and retrieve fitness lectures.
- **Booking System**: Book lectures for specific dates, ensuring that bookings adhere to capacity and date constraints.
- **User Validation**: Validate users before allowing them to make bookings.
- **Unit Tests**: Comprehensive test cases to ensure the correctness of the booking logic.

---

## Project Structure

The project is organized into the following main packages:

### 1. **com.abc.fitness.lecture**

- Manages lecture-related operations.
- Key classes:
  - **LectureService**: Handles logic for creating and retrieving lectures.

### 2. **com.abc.fitness.booking**

- Manages booking-related operations.
- Key classes:
  - **BookingService**: Contains the logic for booking lectures, including validations.

### 3. **com.abc.fitness.test**

- Contains unit tests for the application.
- Key tests:
  - Validating booking scenarios such as non-existent lectures, full classes, invalid dates, etc.

---

## Technologies Used

- **Spring Boot**: Backend framework.
- **H2 Database**: In-memory database for testing.
- **JUnit 5**: For unit testing.
- **Maven**: Build tool.

---

## Prerequisites

- Java 17
- Maven 3.6

---

## Getting Started

### 1. Clone the Repository

bash
git clone [https://github.com/your-repo/fitness-lecture-booking.git](https://github.com/tejeswar5/abc_fitness_repo.git)


### 2. Build the Project

bash
mvn clean install


### 3. Run the Application

bash
mvn spring-boot:run


The application runs on http\://localhost:9999.

### 4. Run Unit Tests

bash
mvn test


---

## API Endpoints

### 1. **Lecture Endpoints**

- **Create a Lecture**

  - **POST** /api/lectures
  - Request Body:
    
json
    {
      "name": "Yoga",
      "startDate": "2024-01-01",
      "endDate": "2024-01-10",
      "startTime": "10:00",
      "capacity": 10
    }


- **Get All Lectures**

  - **GET** /api/lectures

- **Get Lecture by ID**

  - **GET** /api/lectures/{id}

### 2. **Booking Endpoints**

- **Create a Booking**
  - **POST** /api/bookings
  - Request Body:
    
json
    {
      "username": "Krishna",
      "lectureId": 1,
      "participationDate": "2024-01-05"
    }


---

## Validation Rules

- Users must be valid.
- Booking dates must fall within the lecture’s start and end dates.
- A lecture cannot exceed its capacity.

---

## Unit Testing

The project includes comprehensive unit tests in BookingServiceTest to validate booking scenarios, including:

1. Booking for non-existent lectures.
2. Booking for dates outside the lecture’s date range.
3. Booking for lectures that are already full.
4. Booking for invalid or non-existent users.

Run tests with:

bash
mvn test


---

## Future Enhancements

- Integration with an external database.
- Add user authentication and role-based access.
- Enhanced error handling with custom exceptions.

---

## Author

Developed by\
Tejeswar Thota
