@echo off
call mvn clean package
call docker build -t com.mycompany/AdditionEJB .
call docker rm -f AdditionEJB
call docker run -d -p 9080:9080 -p 9443:9443 --name AdditionEJB com.mycompany/AdditionEJB