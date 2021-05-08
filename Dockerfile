FROM adoptopenjdk/openjdk16:alpine AS build
WORKDIR workspace

COPY gradle gradle
COPY settings.gradle .
COPY gradlew .
RUN ./gradlew
COPY build.gradle .
RUN ./gradlew dependencies --refresh-dependencies
COPY src src

RUN ./gradlew bootJar
RUN java -Djarmode=layertools -jar build/libs/*.jar extract

FROM adoptopenjdk/openjdk16:alpine

RUN cp /usr/share/zoneinfo/Europe/Moscow /etc/localtime \
    && echo "Europe/Moscow" >  /etc/timezone

COPY --from=build workspace/dependencies/ .
COPY --from=build workspace/snapshot-dependencies/ .
# allow empty snapshot dependencies
RUN true
COPY --from=build workspace/spring-boot-loader/ .
COPY --from=build workspace/application/ .

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

