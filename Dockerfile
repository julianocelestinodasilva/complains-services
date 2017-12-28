FROM java:8

RUN apt-get update
RUN apt-get install maven -y

WORKDIR ./complains-services

ENV PORT 4567

ADD pom.xml /complains-services/pom.xml
RUN ["mvn", "dependency:resolve"]
# RUN ["mvn", "verify"]

ADD src /complains-services/src
RUN ["mvn", "clean"]
RUN ["mvn", "package"]

EXPOSE 4567
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/complains-services-1.0-SNAPSHOT.jar"]

