# URL_Shortener
A URL Shortener prototype developed for Neueda Technologies

This prototype had been developed with spring boot and Thyme leaf frameworks. Maven is being used as a build tool.

# Prerequisites:
1. JAVA 1.8
2. Maven
3. Latest version of Chrome browser
4. Spring Tool Suite (STS) as IDE in order to to further development on the source code

# How to execute:
1. Clone the repository to your local. Paste the jar file found in <b>URL_Shortener/target/URL_short_Simulator-0.0.1-SNAPSHOT.jar</b> into the folder where <b>pom.xml</b> is present and execute it
2. Get to the folder where pom.xml is present and run the below command <b>mvn clean install</b> to generate a new jar
3. Execute the jar file named URL_short_Simulator-0.0.1-SNAPSHOT.jar using <b>java -jar URL_short_Simulator-0.0.1-SNAPSHOT.jar</b> command
4. once server is started. Go to http://localhost:8085 to load the application (This port number can be customised by changing the <b>server.port</b> property in <b>application.properties file</b> )
5. <b>Swagger</b> documentation is also being provided alon with it.checkout http://localhost:8085/swagger-ui.html to get the list of Rest API endpoints and also to try executing it.
6. Logging mechanism is used to log sensitive method executions to diagnose if any issue occurs in future. Refer <b>application.log</b> file

# Server side implementation :
1. <b>URL_Shortener/src/main/java/com/neueda/url_shortner/URLShortnerStarter.java</b> -> contains the Srping application starter method

2. <b>URL_Shortener/src/main/java/com/neueda/POJO/</b> -> This package holds POJO classes. Here we have ShortURLPOJO.java as a POJO whose object will be used as a model to carry the data from front end to back end vice versa

3. <b>com/neueda/controllers/ShortURLRestapiController.java</b> -> This package holds the rest api controllers. "Restful webservices" were bwing used to establish connection between server and the UI.

# APIs, limitation and Usage
1. <b>GET /</b> GET call. No parameters are required. To return the index or the landing page

2. <b>POST /shortenurl</b> POST call for short url creation. Need to pass a proper full URL

3. <b>GET /s/{randomstring}</b> Need to pass the random string. This will check the map to find the full URL and forward your request to the destination website

4. <b>GET /getURLs</b> GET call. No parameters are required. This would be called internally as a success callback of every short url creation method to keep updating UI with list of short and full URLs

5. <b>GET /getSiteHitCount</b> GET call. No parameters are required. To get the number of our web site hits for statistical analysis

# Client side Implementation
1. HTML,CSS, Angular JS were being used to develop the user interface.

2. Model-View-Controller structure is being followed throughtout the project. Implemented a controller to handle all the required validations.
