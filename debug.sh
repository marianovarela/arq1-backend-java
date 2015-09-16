#!/bin/sh

ABSOLUTE_PATH=`pwd`

HTTPS_PARAMS='-Dserver.port=8043 -Dkeystore.file='${ABSOLUTE_PATH}'/keystore.p12 -Dkeystore.pass=tacpass -Dkeystore.alias=tomcat -Dkeystore.type=PKCS12'
DEBUG_PARAMS='-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n'
DB_PARAMS='-Dspring.jpa.hibernate.ddl-auto=create  -Dspring.datasource.url=jdbc:mysql://localhost:3306/ttac -Dspring.datasource.username=ttacuser -Dspring.datasource.password=ttacpass'

mvn -Dmaven.test.skip=true package


java $DEBUG_PARAMS $DB_PARAMS -jar target/api.jar


## Para usar MySQl como persistencia
##
#java $DEBUG_PARAMS $DB_PARAMS -jar target/api.jar
##

## Para correr en https
##
#java $HTTPS_PARAMS $DEBUG_PARAMS -jar target/api.jar
###

## Para https y MySQL
##
#java $HTTPS_PARAMS $DB_PARAMS $DEBUG_PARAMS -jar target/api.jar
##

##http://thoughtfulsoftware.wordpress.com/2014/01/05/setting-up-https-for-spring-boot/
##keytool -list -v -keystore keystore.p12 -storetype pkcs12
##password: tacpass


##http://blog.getpostman.com/index.php/2014/01/28/using-self-signed-certificates-with-postman/

