#!/bin/sh
mvn clean package && docker build -t com.mycompany/AdditionEJB .
docker rm -f AdditionEJB || true && docker run -d -p 9080:9080 -p 9443:9443 --name AdditionEJB com.mycompany/AdditionEJB