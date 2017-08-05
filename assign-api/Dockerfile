FROM jboss/wildfly:10.1.0.Final

ADD configuration /opt/jboss/wildfly/standalone

# Rename the original configuration file
RUN mv /opt/jboss/wildfly/standalone/configuration/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml.orig

# WildFly configuration file ready for HTTPS
ADD configuration/xml/standalone-full-sample.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

# Add the certificate.sh script into $JBOSS_HOME/standalone/configuration/certs
ADD configuration/certs/ $JBOSS_HOME/standalone/configuration/certs

# Switch to $JBOSS_HOME/configuration/certs
WORKDIR /opt/jboss/wildfly/standalone/configuration/certs

# Execute the script to generate self signed certificates
RUN ./certificate.sh

# Set the working directory to jboss' user home directory
WORKDIR /opt/jboss

# Expose SSL default port
EXPOSE 8443

# CMD ["/opt/jboss/wildfly/configuration/execute.sh"]
