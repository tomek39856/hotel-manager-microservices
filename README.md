# Hotel Microservices

Project is Java implementation of concept described in Udi Dahan's "Advanced Distributed Systems Design using SOA & DDD" course.

[http://udidahan.com/training/](http://udidahan.com/training/)

## Key assumptions

1. Services should not use request-response pattern to communicate between themselves.
2. The only part where request-response is allowed is itops module responsible for security and cross-domain processes which requires collecting data from many modules.
3. Each service exposes it's own frontend component and only this service can present and modify modules data.
4. Composite UI service collects components exposed by domain services and composes frontend views

## Architecture

1. Services are created with Spring Boot. Registers in Eureka Service Discovery. All public URL is exposed by gateway.
2. Domain services communicate in async way (fire & forget architectural style) by Active MQ message broker.
3. ITOPS service collects data from other services using request-response calls. Each service required by itops exposes it's own provider with Feign Client contract definition and DTOs.
4. Domain services exposes their frontend components as static js file with reusable Angular Elements
5. Webapp service  composes UI from Angular Elements components exposed by other services 

## Domain Services

#### Reservation
Responsible for reserving a room, searching available rooms and searching for "no-show" reservations
#### Marketing
Holds and displays room types descriptions
#### Payment
Stores payment data and supports payment successes and failures
#### Guest
Stores guests information and supports searching by guest last name
#### Rate
Delivers information about rates for given room type in particular point of time
#### Occupancy
Supports check-in process and physical room allocation

## Utility Services

#### ITOPS
Communicates with external service to charge card. Collects all of the required data using providers delivered by other modules.
#### Bus
Starts embedded Active MQ broker
#### Gateway
Gateway to public endpoints exposed by services. Uses Zuul
#### Service Discovery
Provides SD functionality by starting Eureka
#### Webapp
Builds UI composed from domain services frontends

## Prerequisites

```
Java 1.8 or higher
nodejs + npm
gradle
```

## Build process
1. Npm frontend build by gradle incremental task, build is started only when sources or package.json changes
2. Gradle copy of frontend project into static resources of spring boot aplication
3. Gradle creates bootJar

## Running

Start
```
gradle bootRun
```
Application is available at
http://localhost:8080/webapp

## Tests

Project contains only backend tests

```
gradle test
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Author

* **Tomasz Tokarczyk** (https://github.com/tomek39856)