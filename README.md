# BookIt
Created CRUD **Restful APIs** for movie ticket booking application using **Spring Boot, MySQL, Spring Data JPA**
# Tech used
Java 17<br><br>
Spring Boot 3.3.3
# Features: 
1. Add the theaters, movies, shows and seats
2. Fetch all the seats of a show
3. Book seats for a show with concurrency handling
4. Update the seat status on payment
5. Schedule the seat status update every minute
# Database Setup:
1. Create MySQL database movie_ticket_booking
2. Change MySQL password and username accordingly<br><br>
open ```src/main/resources/application.properties```<br>
change ```spring.datasource.username``` and ```spring.datasource.password``` as per your mysql installation<br><br>
The app will run at http://localhost:8080.
# Run the project:
Install the dependencies ```./mvnw clean install```<br><br>
Run the application ```./mvnw spring-boot:run```
# Test REST APIs:
You can use PostMan or any other platform to test the APIs as in the controllers<br><br>
/booking<br>
/payment<br>
/show<br>
/movie<br>
/theater<br>







   
   


