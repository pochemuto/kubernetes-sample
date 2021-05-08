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

RUN apk add -U tzdata \
    && cp /usr/share/zoneinfo/Europe/Moscow /etc/localtime \
    && echo "Europe/Moscow" >  /etc/timezone

ARG CERT=YandexInternalRootCA.crt
COPY $CERT .
RUN keytool -importcert -trustcacerts -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt -file $CERT \
    && rm $CERT

COPY --from=build workspace/dependencies/ .
COPY --from=build workspace/snapshot-dependencies/ .
# allow empty snapshot dependencies
RUN true
COPY --from=build workspace/spring-boot-loader/ .
COPY --from=build workspace/application/ .

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

