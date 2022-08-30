
# Health-backend

API REST built with Spring Boot including CRUD operations to manage the clinical case management system of a mental institution.








## Screenshots

![App Screenshot](https://github.com/AlPrats/health-backend/blob/master/screenshots/health4.png?raw=true)

![App Screenshot](https://github.com/AlPrats/health-backend/blob/master/screenshots/health5.png?raw=true)


## Features

- CRUD Consult
- CRUD Exam
- CRUD Medic
- CRUD Patient
- CRUD Specialty
- Richardson Maturity Model

### Upcoming features

- Spring Security + Oauth2 + JWT

## Dependencies

- Spring Web
- Spring Hateoas
- Spring Data JPA
- spring MVC Validation
- ModelMapper
- Lombok

## Requirements

- Maven
- Java 11
- JDK 11 (Oracle or OpenJDK)
- Spring Boot
- PostgreSql


## Installation

To download the code it is necessary to make a local copy of the repository, for that we execute the following command in the console:

```bash
  git-clone https://github.com/AlPrats/health-backend.git
```

Once this is done, we can now open the project and execute the code in our preferred editor.


## Caution

- The database needs to be created and running for the API to be able to communicate with it, otherwise there will be a runtime error.
- The data in the "application.properties" file must be in accordance with the user data of the database server for the connection to be successful.

```bash
#JPA HIBERNATE
spring.jpa.database=postgresql
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

#DATABASE CONNECTION
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost/db_health
spring.datasource.username=your_username
spring.datasource.password=your_password
```



## Explore REST APIs

The app defines following CRUD APIs.

### Consults

#### Get all consults

```http
  GET /consults
```

#### Get consult by Id

```http
  GET /consults/{id}
```

#### Get consult by Id with links

```http
  GET /consults/hateoas/{id}
```

#### Get consult by search of dni / fullname

```http
  GET /consults/search/others
```

#### Get consult by search of dates

```http
  GET /consults/search/date
```

#### Post consult

```http
  POST /consults
```

#### Put consult

```http
  PUT /consults
```

#### Delete consult

```http
  DELETE /consults/{id}
```


### Exams

#### Get all exams

```http
  GET /exams
```

#### Get exam by Id

```http
  GET /exams/{id}
```

#### Get exam by Id with links

```http
  GET /exams/hateoas/{id}
```

#### Post exam

```http
  POST /exams
```

#### Put exam

```http
  PUT /exams
```

#### Delete exam

```http
  DELETE /exams/{id}
```


### Medics

#### Get all medics

```http
  GET /medics
```

#### Get medic by Id

```http
  GET /medics/{id}
```

#### Get medic by Id with links

```http
  GET /medics/hateoas/{id}
```

#### Post medics

```http
  POST /medics
```

#### Put medic

```http
  PUT /medics
```

#### Delete medic

```http
  DELETE /medics/{id}
```


### Patient

#### Get all patients

```http
  GET /patients
```

#### Get patient by Id

```http
  GET /patients/{id}
```

#### Get patient by Id with links

```http
  GET /patients/hateoas/{id}
```

#### Post patient

```http
  POST /patients
```

#### Put patient

```http
  PUT /patients
```

#### Delete patient

```http
  DELETE /patients/{id}
```


### Specialty

#### Get all specialties

```http
  GET /specialties
```

#### Get specialty by Id

```http
  GET /specialties/{id}
```

#### Get specialty by Id with links

```http
  GET /specialties/hateoas/{id}
```

#### Post specialty

```http
  POST /specialties
```

#### Put specialty

```http
  PUT /specialties
```

#### Delete specialty

```http
  DELETE /specialties/{id}
```

You can test them using Postman or any other rest client.


## Tech Stack

**Client:** Postman

**Server:** Java, Spring Boot (Tomcat), PostgreSql

