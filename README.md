This is a spring boot application where a students can register themselves and issued the book. I used JWT authentication for authentication method ,where email and password will be fetched from student table and only these student can access api requests.

1. Install jdk , IDE, MySql, MySql work bench , Postman
2. CLone this Project & import as maven
3. Override application.properties file to configure database
4. Run LibrarySystemApplication class

## **BASIC FEATURES**
1. Student can Signup/Login into Library Management.
2. After login student get a token, that token is used for accessing all the Urls. Student can issued, return book and also get details of themselves whether they issued the book or not. NOTE: All Urls are given below.
3. Student can only issued one book at a time only if that book is available in our database.
4. A teacher/admin can add the book, delete a book, delete all book, get list of all student or a particular student.
**Validations, exceptions are handled throughout the application**

## **STUDENT CONTROLLER URLs**
* http://localhost:8080/api/library/student/register POST-- for registration of student into library management(All fiels are complusory,if you missed any of them you will get 403 error in postman) email should be unique and password will encoded so memorise it. JSON
  
  {"name":"Kumari Chandni","email":"abc@gmail.com","password":"12345a","standard":5,"phone":"78654"}
* http://localhost:8080/api/library/student/login POST-- for login, here student pass only name and email and get the token as output that will used in future from accessing any url. JSON
  
   {"name":"Kumari Chandni","email":"abc@gmail.com"}
* http://localhost:8080/api/library/student/getDetails?stuId=1 GET-- This url is for getting the all information of student by passing the their id into url in place of 1.
* http://localhost:8080/api/library/student/bookIssued?isbn=rd123&stuId=1 GET-- for issuing any book by its isbn number and their student id into url in place of rd123 and 1 respectively.
* http://localhost:8080/api/library/student/returnBook?isbn=rd123&stuId=1 PUT-- for returning the book by this our book again restored in our database.
* http://localhost:8080/api/library/student/findAll GET-- for getting all the book that are present in our database.
* http://localhost:8080/api/library/student/findOne?isbn=rd123 GET-- for getting whether book is present with particular isbn number.

## **TEACHER CONTROLLER URLs**
Teacher/admin can access any url there is no authentication applied for admin.
* http://localhost:8080/api/library/admin/register POST-- for registration of admin/teacher.JSON
  {"name":"assff","email":"wer@gmail.com","password":"ty23"}.
* http://localhost:8080/api/library/admin/addBook POST-- for adding new book into dadabase. JSON
  {"name":"maths","author":"s chand","isbn":"ty12","quantity":5}
  Please provide quantity as 5 only other wise will show error, for this we used quantity as 5.
* http://localhost:8080/api/library/admin/deleteBook?isbn=ty12 DELETE-- for deleting any particular book by passing its isbn number in url
* http://localhost:8080/api/library/admin/deletAllBook DELETE-- for deleting all books.
* http://localhost:8080/api/library/admin/getOneStu?id=1 GET-- for getting any one student by their id number.
* http://localhost:8080/api/library/admin/getAllStu GET-- for getting all student details.
* http://localhost:8080/api/library/admin/deleteOneStu?id=1 DELETE-- for deleting one particular student by passing their id number in url
* http://localhost:8080/api/library/admin/deleteAllStu DELETE-- for deleting all student

## **CONCULSION**
This documentation provides a detailed guide for running the Library Management System, interacting with its API endpoints, and using the authentication system. For further details, refer to the source code or contact Me.


