# Eatclub Takehome Exercise

This exercise make an assumptions based upon the json data provided that if a deal does not contain an open or close time, it would use the open and close time of the restaurant for the deal's open and close time.

Launching the application:

`./mvnw clean spring-boot:run`

To get the all the deals at a certain time:

`curl -X GET "http://localhost:8080/deals?timeOfDay=10:30"`

To get the period with the most deal:

`curl -X GET "http://localhost:8080/deals/peak"`


