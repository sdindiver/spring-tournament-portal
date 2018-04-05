FROM indiver/tournament-base
RUN apt-get update && \
		apt-get install -y \
		-o APT::Install-Recommend=false -o APT::Install-Suggests=false \
		maven

WORKDIR /usr/local
COPY . /spring-tournament-portal/
WORKDIR /spring-tournament-portal
COPY ./docker/dev/entrypoint.sh /usr/local/bin/entrypoint.sh
ENTRYPOINT ["sh", "/usr/local/bin/entrypoint.sh"]
RUN ["chmod", "+x", "/usr/local/bin/entrypoint.sh"]

