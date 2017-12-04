FROM docker.io/tomcat:7.0.75

MAINTAINER chenjian

RUN mkdir -p /usr/local/src/site

ADD target/site-1.0-SNAPSHOT.jar /usr/local/src/site

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/src/site/site-1.0-SNAPSHOT.jar"]