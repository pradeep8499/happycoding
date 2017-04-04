This Project demonstartes the ability to load customer data into ElasticSearch using Spring Boot and JEST as the REST based Java client.
To create a docker image, do the following

Run the gradle build command (using the Docker QuickStart Terminal that came in the Docker Toolbox):

./gradlew build buildDocker
 
Run the new image in a Docker container:

docker run -p 8080:8080 -t target/elasticsearchdemo



