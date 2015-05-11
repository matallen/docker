#FROM centos:centos6
FROM fedora:20
MAINTAINER Siamak Sadeghianfar <ssadeghi@redhat.com>
MAINTAINER Mat Allen <mallen@redhat.com>

USER root
RUN yum -y update;yum clean all

# Install Java + Tools
RUN yum -y install java-1.7.0-openjdk-devel which telnet unzip openssh-server sudo openssh-clients iputils iproute httpd-tools wget tar; yum clean all
ENV JAVA_HOME /usr/lib/jvm/jre

# Install Nexus
RUN mkdir -p /opt/sonatype-nexus /opt/sonatype-work
RUN wget -O /tmp/nexus-latest-bundle.tar.gz http://www.sonatype.org/downloads/nexus-latest-bundle.tar.gz
RUN tar xzvf /tmp/nexus-latest-bundle.tar.gz -C /opt/sonatype-nexus --strip-components=1
RUN rm -rf /tmp/nexus-latest-bundle.tar.gz
RUN useradd --user-group --system --home-dir /opt/sonatype-nexus nexus

ADD nexus.xml /opt/sonatype-work/nexus/conf/nexus.xml
#ADD nexus.properties /opt/sonatype-work/nexus/conf/nexus.properties

RUN chown -R nexus:nexus /opt/sonatype-work /opt/sonatype-nexus

ENV NEXUS_WEBAPP_CONTEXT_PATH /nexus
RUN echo "#!/bin/bash" > /opt/start-nexus.sh
RUN echo "su -c \"/opt/sonatype-nexus/bin/nexus console\" - nexus" >> /opt/start-nexus.sh
RUN chmod +x /opt/start-nexus.sh

CMD ["/opt/start-nexus.sh"]
EXPOSE 8081