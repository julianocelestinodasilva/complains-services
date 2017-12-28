
    - To Build Application and Run Tests :
        This app use Docker container.
        With two docker files (Dockerfile, db/Dockerfile) and a docker-compose file. The first one is the file of the service "complains-services",
        and the second one is the data base service.
	    To build and run the app execute the command "docker-compose up --build" in the root folder.
  
    - End Points :
	    GET  http://172.23.0.2:8080/complain/all