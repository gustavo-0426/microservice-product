FROM openjdk:11
MAINTAINER Gustavo Castro

RUN mkdir -p /home/api-microservice-product-h2/

WORKDIR /home/api-microservice-product-h2

COPY ./target/api-microservice-product-h2-2.0.0.jar ./api-microservice-product-h2-2.0.0.jar

EXPOSE 9090

CMD ["java", "-jar", "/home/api-microservice-product-h2/api-microservice-product-h2-2.0.0.jar"]