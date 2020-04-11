FROM openjdk:8u111-jdk-alpine
ENV MAIN_OPTS '' 
RUN mkdir -p /opt/http-redirector/ /opt/appConfig
COPY target/http-redirector.jar /opt/http-redirector/
WORKDIR /opt/http-redirector/
ENTRYPOINT java $JAVA_OPTS -jar ./http-redirector.jar $MAIN_OPTS
EXPOSE 80
