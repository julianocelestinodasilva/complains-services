
This is the API that exposes the Complain Services.

This app was developed using Java 8, Spring Boot and Postgres Data Base.


    - Build Application and Run Tests :

        This app uses Docker container.
        With two docker files (Dockerfile, db/Dockerfile) and a docker-compose file. The first one is the file of the service "complains-services",
        and the second one is the data base service.
	    To build and run the app execute the command "docker-compose up --build" in the root folder.
	    This project has only Integrated Tests (it is not executed in the Maven tests phase because it's a little slow)


    - End Points (172.23.0.2 - It's the Docker Container IP) :

        GET    : http://172.23.0.2:8080/complains
        GET    : http://172.23.0.2:8080/complains/{id}
        GET    : http://172.23.0.2:8080/complains?company=XXX&city=XXX
        POST   : http://172.23.0.2:8080/complains {Complain Json in the body}
        DELETE : http://172.23.0.2:8080/complains