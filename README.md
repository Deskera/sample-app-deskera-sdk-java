# sample-app-deskera-sdk-java

This is a sample application built with [Sprint Boot](https://spring.io/projects/spring-boot) and [Apache Maven](https://maven.apache.org/).

## How to Use

1. Checkout code  
    ```
    git clone https://github.com/Deskera/sample-app-deskera-sdk-java.git
    ```  
   or download it from https://github.com/Deskera/sample-app-deskera-sdk-java and unzip.
2. Start PostgreSQL database
   If you don't have a database instance available, you may start one with the following Docker command:
    ```
   docker run --rm --name sample_project_java -d -p 5432:5432 -e POSTGRES_USER=sample_project_java \
   -e POSTGRES_PASSWORD={Your database password} -e POSTGRES_DB=sample_project_java postgres:latest
   ```
    and update `application.yaml` in `src/main/resources/` folder.
3. Set up a public Callback URL
    If you cannot expose your application to a public IP, you may use `ngrok` to expose port 8080 over a public URL
    ```
   ./ngrok http 8080
    ```
   Take a note of the public URL because you will have to use in the Step 4, 5 and 6.
   
4. Sign up for Deskera Developer Account
    You may use the following `curl` command to register your app on Deskera API Platform *Sandbox*.
    ```
    curl --location --request POST \
    'https://api-dev.deskera.xyz/oauth/partner' \
    --header 'Content-Type: application/json' \
    --header 'Accept: application/json' \
    --data-raw '{
    "name": {Your App Name},
    "email": {Your Email Id},
    "webServerRedirectUri" : {Your public URL}/oauth2redirect
    }'
   ```
5. Update properties in `client-application.properties` in `src/main/resources/` folder.
    ```
    OAuth2PartnerClientId={Your Client Id}
    OAuth2PartnerSecret={Your Client Secret}
    OAuth2PartnerRedirectUrl={Your public URL}/oauth2redirect
    applicationUrl={Your public URL}
    deskeraOAuthUri=https://appauth-dev.deskera.xyz
    environment=DEV
    ```
6. Build the project 
    You may build the project using Maven Wrapper included with the source. Navigate to the project root and run
    ```
    ./mvnw clean install
    ```
7. Run the Spring Boot application
   Navigate to the project root and run
   ```
   ./mvnw spring-boot:run 
   ```
8. Navigate to `{Your public URL}` in your browser

## License

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/Deskera/sample-app-deskera-sdk-java/raw/master/LICENSE)