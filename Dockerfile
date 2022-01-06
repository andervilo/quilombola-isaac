FROM openjdk:8
COPY target/sigequi.war sigequi.war
RUN bash -c 'touch /sigequi.war'
ENTRYPOINT ["java","-jar","/sigequi.war"]
EXPOSE 8080