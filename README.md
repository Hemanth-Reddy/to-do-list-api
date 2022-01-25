# To-Do-List API: 

### *Description:*

This project demonstrates a to-do list api. The api provides support for user-specific to-do list i.e. any user with access to this api can register and login and list down his/her to-do task and save it for further use.

### *Softwares Required:*

The projects in this repo require the following softwares: 
* Java
* Any IDE like Eclipse, IntelliJ, etc.
* Docker(for building images, running containers, etc.)
* Postman or swagger(for validation of web service api's)

### *Building and running steps:*

The project contains the docker-compose file which specifies the configuration required for the containers used in this application.

#### *Images used:*

The docker images used in this application are:
* application image - name -> hemanth159/to-do-list:0.0.1-SNAPSHOT
* postgres official image - name -> postgres

#### *Docker commands to run in isolation:*

Docker commands to individually run the images instead of docker-compose(order of execution matters and it is as given below):
* Initially, create a network temp-network using the following commands:
```
docker network create temp-network
```
* Then, using the below command, postgres image can be run:
```
docker run --name dbpostgres --net temp-network -p 5432:5432 -e POSTGRES_PASSWORD=seneca123$ postgres
```
* To run the application image, the following docker command is used:
```
docker run --net temp-network --env-file envFile.txt -p 8080:8080 hemanth159/to-do-list:0.0.1-SNAPSHOT
```

envFile.txt:  
SPRING_DATASOURCE_URL=jdbc:postgresql://dbpostgres:5432/postgres  
SPRING_DATASOURCE_USERNAME=postgres  
SPRING_DATASOURCE_PASSWORD=seneca123$  

### *Usage:*

This project provides an easy to use secured to-do list api with multiuser feature.








