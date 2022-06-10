Coverage: 81.5%
# Books Reviews Application

This is an application tha can be used through a front end website to create read and update books and reviews. It uses a relational database, where reviews have a many to one relationship with books. i.e one book can have many reviews

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

###

What things you need to install the software and how to install them

```
You will need: Java version 1.8, the latest mySQL version 8.0.19 or higher.
```

### Installing and Deployment

A step by step to get the application running

Steps:

```
1) Open target folder, git bash here 
2) Enter "java -jar BookReviews-0.1.0-SNAPSHOT.jar" into the terminal
3) The applictaion should be running now
4) now go to src/main/respources/static/front-end and open index.html
5) This will open in your browser and the application is ready to use
```

To use the application:

```
Enter the required inputs to create,read update and delete books and reviews.
```

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Tests 
Tests that this application went through

```
JUNIT tests were taken with a coverage of 81.5% to ensure that the CRUD methods were working correctly. 
Mockito was used on the service classes so that they could be tested in isolation, ensuring all of the functions work.
MockMvc Was used to test the controller classes to ensure that the correct HTTP responses were being recieved, as well as the correct response content.
Selenium was also used to run automated tests on the front-end of the application, making sure all of the inputs and buttons work and output what is expected.
Postman was used to manually test the database connection and CRUD functions
```


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [spring](https://spring.io/) - Back-end development

## Versioning

Version 0.1.0.

## Acknowledgments

* QA courseware
* Richard Mansworth
* Stackoverflow
* W3schools

## Link to Jira 
https://saad98.atlassian.net/jira/software/projects/BOOK/boards/4/backlog
## Link to Selenium Testing
https://github.com/5saad/BookReviewSelenium