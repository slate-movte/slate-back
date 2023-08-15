FROM azul/zulu-openjdk-alpine:17-latest 

ENV TZ Asia/Seoul
RUN	cp /usr/share/zoneinfo/${TZ} /etc/localtime && \
    echo "${TZ}" > /etc/timezone && \
    cat "/etc/localtime"

VOLUME /tmp

WORKDIR app

ARG JAV_FILE=./build/libs/*.jar

COPY ${JAV_FILE} app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=${PROFILE}" ,"app.jar"]