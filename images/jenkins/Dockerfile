#FROM centos:centos6
#FROM mallen/dind
FROM fedora:20

# each line is an image, so try to do a lot on a single line
# yum clean all must be on the same line as yum install

MAINTAINER Mat Allen <mallen@redhat.com>

USER root
RUN yum -y update;yum clean all

############################################
# Install Java + Tools
############################################
RUN yum -y install java-1.7.0-openjdk-devel which telnet unzip openssh-server sudo openssh-clients iputils iproute httpd-tools wget tar; yum clean all
ENV JAVA_HOME /usr/lib/jvm/jre

############################################
# Install Jenkins
############################################
RUN wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
RUN rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
RUN yum -y install jenkins

RUN cp /etc/sysconfig/jenkins /etc/sysconfig/jenkins.save
RUN sed -i 's/^JENKINS_ARGS=.*/JENKINS_ARGS="--prefix=\/jenkins"/' /etc/sysconfig/jenkins

############################################
# Install Maven
############################################
#RUN wget -O /etc/yum.repos.d/epel-apache-maven.repo http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo
#RUN yum -y install apache-maven

ENV MAVEN_VERSION 3.2.5
RUN curl -sSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
&& mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/apache-maven \
&& ln -s /usr/share/apache-maven/bin/mvn /usr/bin/mvn


############################################
# Install Git
############################################
RUN yum -y install git

############################################
# Configure Jenkins
############################################
# Jenkins settings 
ADD config.xml /var/lib/jenkins/config.xml

# Maven
ADD hudson.tasks.Maven.xml /var/lib/jenkins/hudson.tasks.Maven.xml

# Plugins
RUN mkdir -p /var/lib/jenkins/plugins
RUN wget -O /var/lib/jenkins/plugins/git.hpi http://updates.jenkins-ci.org/download/plugins/git/2.2.4/git.hpi
RUN wget -O /var/lib/jenkins/plugins/git-client.hpi http://updates.jenkins-ci.org/download/plugins/git-client/1.10.1/git-client.hpi
RUN wget -O /var/lib/jenkins/plugins/scm-api.hpi http://updates.jenkins-ci.org/download/plugins/scm-api/0.2/scm-api.hpi
RUN wget -O /var/lib/jenkins/plugins/greenballs.hpi https://updates.jenkins-ci.org/download/plugins/greenballs/1.14/greenballs.hpi


RUN chown -R jenkins:jenkins /var/lib/jenkins

############################################
# Install Supervisor
############################################
#RUN wget -O /tmp/epel-release-6-8.noarch.rpm http://download.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm 
#RUN rpm -ivh /tmp/epel-release-6-8.noarch.rpm

RUN yum -y install supervisor
RUN echo "[program:jenkins]" >> /etc/supervisord.conf
RUN echo "command=/etc/init.d/jenkins start" >> /etc/supervisord.conf
RUN echo "user=root" >> /etc/supervisord.conf


############################################
# Start Jenkins
############################################
CMD ["/bin/supervisord", "-n"]
EXPOSE 8080

