# Spring Boot Java Challenge

This is my solution to the [Ada School's Spring Boot Java Challenge](https://github.com/ada-school/spring-boot-java-challenge).

## Directions

### Coding Challenge 

A bus company wants to start using technology and allow their users to book online tickets. Please help them build a [REST API Level 2](https://martinfowler.com/articles/richardsonMaturityModel.html#level2) (preferrable) that lets them control their trip bookings, supporting the following operations:
* Create a new booking with the following information: name, email, origin, destination, departure date and time and duration.
* Update an existing booking
* Find a booking using its ID.
* Delete an existing booking.

#### Expected Quality Attributes:
* Using coding best practices.
* SOLID principles.
* Clear usage of Dependency injection.
* Correct usage of Spring Boot annotations.
* Correct connection with a persistance layer.

#### Desired technology stack:
* Java / Kotlin 
* Spring Boot
* MongoDB

## Thoughts

- Debatable if you should just use one collection object versus splitting the object into more documents
- You would first signup and create an account.  After that, you would be able to purchase a ticket.  
- One to many relationship?  A person can buy multiple tickets. 
- Make ticket an embedded document inside person document?  
- Embedded documents create data duplication, which increases storage requirements.  However, embedded documents make querying for data easier.  
- This [article](https://spring.io/blog/2021/11/29/spring-data-mongodb-relation-modelling) gives an overview on how to use `DBRefs` and `@DocumentReference` to replace embedded documents. 
- I decided to take an approach similar to the article.  
- Added basic CORS config versus having to annotate methods or controllers.
- Debatable if adding `final` to parameters is really necessary. It doesn't really hurt, and it helps make you more deliberate about what you are doing in the code.

## Continued Development

- Could add Thymeleaf for a frontend.  
- Testing 
- CommandLineRunner to seed database
- `Duration` is problematic inside entities.  Need to investigate more.  Might need to use `@Transient` and store the duration in the database as a string.  

## Useful Resources

- [Bootify](https://bootify.io/)
- [YouTube](https://www.youtube.com/watch?v=vl6DstgPoW8) - MongoDB with Spring Boot Crash Course - Full Tutorial
- [Blog](https://lankydan.dev/2017/05/29/embedded-documents-with-spring-data-and-mongodb) - embedded documents with spring data and mongo
- [Mongo](https://www.mongodb.com/basics/embedded-mongodb#:~:text=Embedded%20documents%20are%20an%20efficient,only%20when%20they're%20worthwhile.) - embedded documents
- [Mongo](https://www.mongodb.com/compatibility/spring-boot) - spring boot 
- [Baeldung](https://www.baeldung.com/spring-data-mongodb-tutorial) - spring data mongodb tutorial
- [Github](https://github.com/SaiUpadhyayula/spring-boot-mongodb-tutorial) - spring boot mongodb tutorial
- [Digital Ocean](https://www.digitalocean.com/community/tutorials/spring-boot-mongodb) - spring boot mongodb
- [Github](https://ucsb-cs156.github.io/topics/mongodb/mongodb_spring_boot_nested_collection.html) - mongodb spring boot nested collection
- [Stack Overflow](https://stackoverflow.com/questions/61405644/how-to-update-embedded-objects-in-spring-mongodb) - how to update embedded objects in spring mongodb
- [Java tech online](https://javatechonline.com/spring-boot-mongodb-using-mongotemplate-examples/) -  spring boot mongodb using mongotemplate examples
- [Medium](https://medium.com/@AlexanderObregon/building-and-consuming-rest-apis-in-spring-microservices-23b5d12dd6b4) - building and consuming rest apis in spring microservices
- [Mongo](https://www.mongodb.com/compatibility/spring-boot) - spring boot
- [YouTube](https://www.youtube.com/watch?v=ReqMU6bmPNM) - MongoDB – Model One-to-One, One-to-Many Relationships Embedded Documents | Spring Boot | Java Techie
- [Medium](https://medium.com/@beheradebananda000/mongodb-vs-mongodb-srv-whats-the-difference-15d0baa1a1a9) - mongodb vs mongodb srv whats the difference
- [Blog](https://alexbevi.com/blog/2023/11/13/querysrv-errors-when-connecting-to-mongodb-atlas/) - querysrv errors when connecting to mongodb atlas
- [Baeldung](https://www.baeldung.com/spring-profiles) - spring profiles
- [YouTube](https://www.youtube.com/watch?v=ORVcw6bQ0z4) - Spring Boot MongoDB CRUD 2024 | REST API Operations Tutorial
- [YouTube](https://www.youtube.com/watch?v=G6FRdtyg1AE) - MongoDB CRUD operation using Springboot
- [Stack Overflow](https://stackoverflow.com/questions/56913429/how-to-create-an-id-within-the-embedded-document-using-mongodb-and-spring-data) - how to create an id within the embedded document using mongodb and spring data
- [Baeldung](https://www.baeldung.com/spring-boot-mongodb-auto-generated-field) - spring bot mongodb auto generated field
- [Stack Overflow](https://stackoverflow.com/questions/42087787/how-to-generate-uuid-using-spring-annotations) - how to generate uuid using spring annotations
- [Spring](https://docs.spring.io/spring-data/mongodb/reference/index.html) - mongodb reference
- [Github](https://github.com/spring-projects/spring-data-mongodb/issues/4670) - Problem with replacement of `@DBRef` to `@DocumentReference` 
- [Bootify](https://bootify.io/mongodb/document-reference-in-spring-boot-mongodb.html) - document reference in spring boot mongodb
- [Stack Overflow](https://stackoverflow.com/questions/28427525/how-to-model-java-time-duration-in-mysql-database) - how to model java time duration in mysql database
- [Stack Overflow](https://stackoverflow.com/questions/59369490/spring-thymeleaf-template-does-not-work-upon-adding-cors-configuration) - thymeleaf template does not work upon adding cors configuration
