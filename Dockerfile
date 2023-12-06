FROM artifactory.cotiviti.com/docker/openjdk:17-alpine
LABEL MAINTAINER charles.bachetti@cotiviti.com

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
# COPY krb5.conf /etc/krb5.conf
# COPY jaas.conf /etc/jaas.conf
# COPY svcdrcacob.keytab /etc/svcdrcacob.keytab

# HTTPS
EXPOSE 8443

RUN echo "This is the JAR filename: $JAR_FILE"
HEALTHCHECK --interval=30s --timeout=30s --retries=3 CMD wget --spider http://localhost:8443/coblog/actuator/health || exit 1

# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java","-Djava.security.auth.login.config=/opt/cob/jaas.conf","-Djava.security.krb5.conf=/opt/cob/krb5.conf","-jar","app.jar"]
