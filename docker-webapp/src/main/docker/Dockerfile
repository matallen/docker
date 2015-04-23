#FROM centos:centos6
FROM fedora:20

MAINTAINER mallen <mallen@redhat.com>

# Dependencies
RUN yum -y install java-1.7.0-openjdk-devel unzip wget; yum clean all

# Make sure JAVA_HOME is set
ENV JAVA_HOME /usr/lib/jvm/jre


# Download JDK
#RUN cd /opt;wget https://s3.amazonaws.com/jdk-7u67-linux-x64.tar.gz


#gunzip JDK
#RUN cd /opt;gunzip jdk-7u67-linux-x64.tar.gz
#RUN cd /opt;tar xvf jdk-7u67-linux-x64.tar
#RUN alternatives –install /usr/bin/java java /opt/jdk1.7.0_67/bin/java 2

# untar and move to proper location
#RUN cd /tmp;gunzip apache-tomcat-7.0.55.tar.gz
#RUN cd /tmp;tar xvf apache-tomcat-7.0.55.tar
#RUN cd /tmp;mv apache-tomcat-7.0.55 /opt/tomcat7
#RUN chmod -R 755 /opt/tomcat7

#ENV JAVA_HOME /opt/jdk1.7.0_67

# Download Apache Tomcat 7
#RUN cd /tmp;wget https://s3.amazonaws.com/apache-tomcat-7.0.55.tar.gz
RUN cd /tmp;wget -O tomcat.zip http://search.maven.org/remotecontent?filepath=org/apache/tomcat/tomcat/7.0.55/tomcat-7.0.55.zip
RUN cd /tmp;unzip tomcat.zip
RUN cd /tmp;mv apache-tomcat-7.0.55 /opt/tomcat7
RUN chmod -R 755 /opt/tomcat7
ADD docker-webapp.war /opt/tomcat7/webapps/docker-webapp.war

EXPOSE 8080

CMD /opt/tomcat7/bin/catalina.sh run
