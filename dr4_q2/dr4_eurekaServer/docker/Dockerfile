FROM amazoncorretto:17
LABEL author="luanribeir"
WORKDIR /app
COPY  target/dr4_eurekaServer-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]