FROM amazoncorretto:11.0.19
MAINTAINER Gustavo Castro

RUN mkdir -p /home/api-microservice
WORKDIR /home/api-microservice
COPY ./target/api-microservice-product-h2-1.0.0.jar ./api-microservice-1.0.0.jar
CMD ["java", "-jar", "/home/api-microservice/api-microservice-1.0.0.jar"]