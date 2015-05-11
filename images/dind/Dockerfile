#FROM centos:centos6
FROM fedora:20
MAINTAINER amitsaha.in@gmail.com

# Let's start with some basic stuff.
#USER root
#RUN yum -y erase docker
RUN yum -y install which wget

#RUN wget -O /tmp/epel-release-6-8.noarch.rpm http://download.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm 
#RUN rpm -ivh /tmp/epel-release-6-8.noarch.rpm

RUN yum -y update;yum clean all
RUN yum -y install docker-io iptables ca-certificates lxc e2fsprogs; yum clean all

# Install Docker from Fedora repos
#RUN sudo yum -y install docker-io

# Install the magic wrapper.
ADD ./wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

# Define additional metadata for our image.
VOLUME /var/lib/docker
EXPOSE 4243
CMD ["wrapdocker"]