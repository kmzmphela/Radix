# Loan & Payment Management API

## Overview

This project is a Spring Boot REST API for managing **Loans** and **Payments**.
The system allows users to create loans, make payments toward those loans, and automatically updates the loan status when fully repaid.

The application uses an **H2 in-memory database** and includes unit tests to validate core functionality.

---

## Tech Stack

* Java 26
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* JUnit

---

## Project Structure

The project is structured using **domain separation**, where Loan and Payment are isolated into their own packages:

```
src/
 ├── main/
 │   ├── java/com/radix/assessment/
 │   │
 │   │   ├── loan/
 │   │   │   ├── controller/
 │   │   │   │   └── LoanController.java
 │   │   │   ├── entity/
 │   │   │   │   └── Loan.java
 │   │   │   ├── repository/
 │   │   │   └── service/
 │   │   │
 │   │   ├── payment/
 │   │   │   ├── controller/
 │   │   │   │   └── PaymentController.java
 │   │   │   ├── entity/
 │   │   │   │   └── Payment.java
 │   │   │   ├── repository/
 │   │   │   └── service/
 │   │   │
 │   │   └── DemoApplication.java
 │   │
 │   └── resources/
 │       └── application.yml
 │
 └── test/
     └── java/com/example/demo/
         ├── loan/
         └── payment/
```

---

## Domain Models

### Loan Domain

**Entity: Loan**

| Field      | Type    | Description        |
| ---------- | ------- | ------------------ |
| loanId     | Long    | Unique identifier  |
| loanAmount | Double  | Total loan amount  |
| term       | Integer | Duration in months |
| status     | Enum    | ACTIVE or SETTLED  |

---

### Payment Domain

**Entity: Payment**

| Field         | Type   | Description       |
| ------------- | ------ | ----------------- |
| paymentId     | Long   | Unique identifier |
| loanId        | Long   | Associated loan   |
| paymentAmount | Double | Amount paid       |

---

## API Endpoints

### Loan APIs

#### Create Loan

```
POST /loans
```

**Request Body**

```json
{
  "loanAmount": 10000,
  "term": 12
}
```

**Response**

```json
{
  "loanId": 1,
  "loanAmount": 10000,
  "term": 12,
  "status": "ACTIVE"
}
```

---

#### Get Loan by ID

```
GET /loans/{loanId}
```

---

### Payment APIs

#### Record Payment

```
POST /payments
```

**Request Body**

```json
{
  "loanId": 1,
  "paymentAmount": 2000
}
```

---

## Business Rules

* Payments reduce the outstanding loan balance.
* Overpayment is **not allowed** and results in an error.
* When total payments equal the loan amount:

    * Loan status is updated to `SETTLED`.

---

## Database Configuration

The application uses an **H2 in-memory database**.

### H2 Console

```
http://localhost:8080/h2-console
```

### Default Credentials

```
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (empty)
```

---

## How to Build and Run

### Maven

```
mvn clean install
mvn spring-boot:run
```


Application runs at:

```
http://localhost:8080
```

---

## Running Tests

### Maven

```
mvn test
```


---

## Unit Testing

The following scenarios are covered:

* Loan creation is persisted correctly
* Payments reduce the loan balance
* Overpayment throws an exception
* Loan status updates to `SETTLED` when fully paid

---

## Testing the APIs

### Using curl

#### Create Loan

```
curl -X POST http://localhost:8080/loans \
-H "Content-Type: application/json" \
-d '{"loanAmount":10000,"term":12}'
```

#### Get Loan

```
curl http://localhost:8080/loans/1
```

#### Make Payment

```
curl -X POST http://localhost:8080/payments \
-H "Content-Type: application/json" \
-d '{"loanId":1,"paymentAmount":2000}'
```

---

### Using Postman

1. Create a new collection
2. Add endpoints:

    * `POST /loans`
    * `GET /loans/{loanId}`
    * `POST /payments`
3. Set header:

   ```
   Content-Type: application/json
   ```

---

## Error Handling

Common errors include:

* Loan not found → `404`
* Overpayment attempt → `400`
* Invalid input → `400`

---

## Future Improvements

* Add authentication (JWT / OAuth2)
* Replace H2 with PostgreSQL/MySQL
* Add interest calculation
* Add payment history endpoint
* Docker support

---

## Notes

* The database is **in-memory**, so all data is lost when the app stops.
* This project is intended for demonstration and assessment purposes.

---
