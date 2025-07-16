# Eatclub Takehome Exercise

This exercise make an assumptions based upon the json data provided that if a deal does not contain an open or close time, it would use the open and close time of the restaurant for the deal's open and close time.

Launching the application:

`./mvnw clean spring-boot:run`

To get the all the deals at a certain time:

`curl -X GET "http://localhost:8080/deals?timeOfDay=10:30"`

To get the period with the most deal:

`curl -X GET "http://localhost:8080/deals/peak"`

### DB Design

Attached file `DB Schema.pdf` is the database schema that would satisfy the data models here.

We only need three tables: Restaurant, Cuisine and Deal. 

#### Restaurant
This table is responsible to capture meta data for the restaurant such as its name, address, etc. These data seldom change

#### Cuisine
This table capture the type of food the restaurant would serve. Data here change more frequent than the restaurant meta data

### Deal
This table capture the active/inactive deals that the restaurant has. This data would change much more frequent based upon the restaurant's decision. 

### Database choice

We can simply use MySQL to model these data. However, we can have separate database for restaurant's meta data from the deals data. Since deals data will be written and read much more often than the other type of data, keeping it separate would allow us to scale better in term of read/write access

Once we move to a more dynamic pricing model for deals, we can move the deals data from MySQL to DynamoDB. 

Notice that we do not need atomic transactions between these data (deals and restaurant), as such we can opt for NoSQL solution to increase read and write throughput.

Taking this further, we can cache a lot of the deals data into a redis to ensure that we can perform quick calculation such as, best deals per user, recommended deals, automated dynamic deals given the restaurant spending limit, etc

