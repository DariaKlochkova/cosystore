FROM openjdk:8-jdk-alpine
COPY ./target/cosystore-1.0-SNAPSHOT.jar /usr/src/cosystore/cosystore.jar
RUN mkdir uploads
RUN chmod 777 ./uploads -R
WORKDIR /usr/src/cosystore
EXPOSE 8080
CMD ["java", "-jar", "cosystore.jar"]