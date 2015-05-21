FROM mallen/jenkins

MAINTAINER Mat Allen <mallen@redhat.com>

ADD settings.xml /var/lib/jenkins/maven-settings.xml

ADD config.xml /var/lib/jenkins/config.xml

RUN mkdir -p /var/lib/jenkins/plugins
RUN wget -O /var/lib/jenkins/plugins/jquery.hpi http://updates.jenkins-ci.org/download/plugins/jquery/1.11.2-0/jquery.hpi
RUN wget -O /var/lib/jenkins/plugins/parameterized-trigger.hpi http://updates.jenkins-ci.org/download/plugins/parameterized-trigger/2.25/parameterized-trigger.hpi
RUN wget -O /var/lib/jenkins/plugins/build-pipeline-plugin.hpi http://updates.jenkins-ci.org/download/plugins/build-pipeline-plugin/1.4.7/build-pipeline-plugin.hpi
#RUN wget -O /var/lib/jenkins/plugins/sonar.hpi http://updates.jenkins-ci.org/download/plugins/sonar/2.1/sonar.hpi
RUN wget -O /var/lib/jenkins/plugins/docker-build-step.hpi http://updates.jenkins-ci.org/download/plugins/docker-build-step/1.22/docker-build-step.hpi
RUN wget -O /var/lib/jenkins/plugins/envinject.hpi http://updates.jenkins-ci.org/download/plugins/envinject/1.91.2/envinject.hpi

#RUN for plugin in jquery parameterized-trigger build-pipeline-plugin sonar docker-build-step envinject ws-cleanup ;\  
#    do                                                                      \  
#        curl -sf -o $JENKINS_HOME/plugins/${plugin}.hpi                     \  
#             -L $JENKINS_MIRROR/plugins/${plugin}/latest/${plugin}.hpi ;    \  
#    done  

#ADD sonar.xml /var/lib/jenkins/hudson.plugins.sonar.SonarPublisher.xml

RUN mkdir -p /var/lib/jenkins/jobs/1-webapp-build
ADD 1-build-job.xml /var/lib/jenkins/jobs/1-webapp-build/config.xml
RUN mkdir -p /var/lib/jenkins/jobs/2-webapp-package
ADD 2-package-job.xml /var/lib/jenkins/jobs/2-webapp-package/config.xml
RUN mkdir -p /var/lib/jenkins/jobs/3-webapp-acceptance-test
ADD 3-acceptance-test-job.xml /var/lib/jenkins/jobs/3-webapp-acceptance-test/config.xml
RUN mkdir -p /var/lib/jenkins/jobs/4-webapp-publish
ADD 4-publish-job.xml /var/lib/jenkins/jobs/4-webapp-publish/config.xml
RUN mkdir -p /var/lib/jenkins/jobs/5-webapp-deploy-to-dev
ADD 5-deploy-to-dev-job.xml /var/lib/jenkins/jobs/5-webapp-deploy-to-dev/config.xml


#Run Jenkins as jenkins user
#RUN chown -R jenkins:jenkins /var/lib/jenkins

#Run Jenkins as root user
RUN chown -R root:root /var/lib/jenkins
RUN cp /etc/sysconfig/jenkins /etc/sysconfig/jenkins.save
RUN cat /etc/sysconfig/jenkins.save | sed '/^#/ d' | sed '/^$/ d' | sed 's/JENKINS_USER="jenkins"/JENKINS_USER="root"/g' > /etc/sysconfig/jenkins


############################################
# Docker-in-docker
############################################
RUN yum -y install docker-io iptables ca-certificates lxc e2fsprogs; yum clean all
#ADD ./wrapdocker /usr/local/bin/wrapdocker
#RUN chmod +x /usr/local/bin/wrapdocker
#RUN echo "[program:docker]" >> /etc/supervisord.conf
#RUN echo "command=/usr/local/bin/wrapdocker" >> /etc/supervisord.conf
#RUN echo "user=root" >> /etc/supervisord.conf
#VOLUME /var/lib/docker
#VOLUME /var/run

# I need to run this to listen on a TCP port and forward to a UNIX socket
# socat TCP-LISTEN:1234,reuseaddr,fork UNIX-CLIENT:/var/run/docker.sock

# using socat to create a local TCP proxy on port 4243 pointing to docker.sock 
RUN yum install -y socat
RUN echo "[program:dockerproxy]" >> /etc/supervisord.conf
RUN echo "command=socat TCP-LISTEN:4243,reuseaddr,fork UNIX-CLIENT:/var/run/docker.sock" >> /etc/supervisord.conf
RUN echo "user=root" >> /etc/supervisord.conf

CMD ["/bin/supervisord", "-n"]
EXPOSE 8080
