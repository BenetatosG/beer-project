# Getting Started

### Reference Documentation

* First build project by running ```./gradlew clean build```
* Start the server by running ```./gradlew bootRun``` <br>
This will start the spring boot application on port 8080 with the REST context path /api<br>
* Go to http://localhost:8080/api/swagger-ui/#/beer-resource and expand beer-resource
    * From here you can use the GET /beers to get beers from punk api. 
      You are able to search by fermentation type, IBU (min,max) and matching food
    * Then use a beer from the json response to save favorite beer using POST /beers/favorites
    * List all your favorite beers with GET /beers/favorites
    * Grab a specific favorite beer by its identifier using GET /beers/favorites/{id}