FROM maven as build
WORKDIR /usr/local
RUN mkdir app
COPY . /usr/local/app/spring-tournament-portal/
WORKDIR /usr/local/app/spring-tournament-portal
RUN mvn -e package




FROM tomcat
ENV PORT 8080
ENV VERSION 0.0.1-SNAPSHOT
ENV ARTIFACT spring-tournament-portal
ENV JPDA_ADDRESS 8000
ENV JPDA_TRANSPORT dt_socket
COPY --from=build /usr/local/app/${ARTIFACT}/target/${ARTIFACT}-${VERSION}  /usr/local/tomcat/webapps/${ARTIFACT}-${VERSION}
WORKDIR /usr/local/tomcat/bin
CMD ["catalina.sh jpda run"]
EXPOSE ${PORT}
