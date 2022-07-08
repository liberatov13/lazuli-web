FROM maven:3-jdk-8
LABEL Name="lazuli-web" Descrtion="Lazuli Web Application" Author="Elvis Liberato de Barros"
LABEL org.opencontainers.image.source https://github.com/liberatov13/lazuli-web
WORKDIR /opt/lazuli-web
ADD ./target /opt/lazuli-web

ENTRYPOINT [ "java", "-jar", "lazuli-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8098
