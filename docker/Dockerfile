FROM 168824777132.dkr.ecr.ap-southeast-1.amazonaws.com/ctx-centos-base:latest

ENV TZ=Asia/Singapore
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY /logs /data/logs
COPY app.jar /usr/app/app.jar

EXPOSE 8080 8081

CMD ["java", "-jar", "-Xmx512m", "-Xms512m","-Xmn256m","-XX:NewRatio=4","-XX:SurvivorRatio=4", "/usr/app/app.jar"]
