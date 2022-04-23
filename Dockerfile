FROM maven:3-jdk-8
LABEL Name="lazuli-web" Descrtion="Lazuli Web Application" Author="Elvis Liberato de Barros"
WORKDIR /opt/lazuli-web
ADD ./target /opt/lazuli-web
ENTRYPOINT [ "mvn", "spring-boot:run" ]
EXPOSE 8080
