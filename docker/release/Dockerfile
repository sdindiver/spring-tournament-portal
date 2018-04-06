FROM indiver/tournament-release
ENV ARTIFACT=spring-tournament-portal
ENV VERSION=0.0.1-SNAPSHOT
ADD ./docker/release-app/ /opt/tomcat/webapps/
WORKDIR /opt/tomcat/bin
ENTRYPOINT ["catalina.sh", "jpda", "run"]
EXPOSE ${PORT}