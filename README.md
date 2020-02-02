# Bloomberg Math Expression API

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Math Expression API is a set of micro-services API responsible to perform math operations.

# New Features!

  - Service Registration and Discovery
  - RESTful APIs
  - i18n added! You can use the header Accept-Language with either `en` or `pt` to see the error messages in these languages.

### Tech

Math Expression API was developed using the technologies bellow:

* [Java] - Java 12
* [Spring Boot] - Spring Boot 2
* [Eureka] - Service Registration and Discovery
* [Lombok] - Lombok
* [ModelMapper] - Model Mapper
* [Docker] - Docker
* [Docker-Compose] - Docker Compose
* [Gradle] - Gradle
* [JUnit5] - JUnit 5
* [Mockito] - Mockito
* [Postman] - Postman


### Installation

Math Expression API need to be cloned on GitHub.

```sh
$ git clone https://github.com/vfcarmo/bloomberg.git
$ cd bloomberg
```

### Running the application

Test the end-to-end result by starting the eureka-server first, next starting the calculator-api and then, once those
have loaded, starting the math-expression.

To make this task easy, you should execute the 'build-docker.sh'. 

First you should make this file executable:
```sh
$ chmod +x build-docker.sh
```
Then you can run this file with sudo (its necessary because the docker-compose command require it):
```sh
$ sudo ./build-docker.sh
```
You should wait all services be available, after that you can make the requests for test.

In this project, the math-expression gets the result of math operation from calculator-api. We use eureka-service, 
which implement service registration and discovery, to be possible one service call the other one.   

![alt text](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuShBJqbLS2qjIirE34ujAaijWeZu9fPak5OK5AKM5sVcvq74d1Dpaajp4aloYnmBChcGzIZewQ7fKB1ISCaiJiu7IbQYAqKr24KbcY6wkXXgau5yBeVKl1IWum40 "Component diagram")

You can test the Math Expression API using [Postman] and importing the collection 
(MathExpression.postman_collection.json) located in the bloomberg directory. 


### Todos

 - Delivery in a Docker Orchestration environment

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Java]: <https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html>
   [Spring Boot]: <https://spring.io/blog/2019/10/16/spring-boot-2-2-0>
   [Eureka]: <https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html>
   [Lombok]: <https://projectlombok.org/>
   [ModelMapper]: <http://modelmapper.org/>
   [Docker]: <https://www.docker.com/>
   [Docker-Compose]: <https://docs.docker.com/compose/>
   [Postman]: <https://www.getpostman.com>
   [Gradle]: <https://gradle.org/>
   [JUnit5]: <https://junit.org/junit5/>
   [Mockito]: <https://site.mockito.org/>
