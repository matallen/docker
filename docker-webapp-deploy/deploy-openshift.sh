
# Script should have the jenkins env variable ${JOB_URL} passed as #1 parameter

HOST=localhost
SSH_USERNAME=mallen
SSH_PASSWORD=
JOB_URL=$1

echo "HOST=$HOST"
echo "USER=$SSH_USERNAME"
echo "PASS=$SSH_PASSWORD"
echo "JOB =$JOB_URL"

ssh $SSH_USERNAME@$SSH_HOST

osc login -u Admin
osc delete project project-docker-webapp
osc new-project project-docker-webapp --display-name="Docker Webapp"

cd /root

wget $JOB_URL/ws/docker-webapp-deploy/kubernetes_create-replication-controller.json
wget $JOB_URL/ws/docker-webapp-deploy/kubernetes_create-service.json
osc create -f kubernetes_create-replication-controller.json
osc create -f kubernetes_create-service.json
