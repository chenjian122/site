FROM e36291f93bed

MAINTAINER chenjian

RUN mkdir -p /usr/local/src/site

ADD target/site-1.0-SNAPSHOT.jar /usr/local/src/site

ENTRYPOINT ["java", "-jar", "/usr/local/src/site/site-1.0-SNAPSHOT.jar"]