

TARGET_SERVER_DOCKER_TCP=192.168.122.153:4243
DOCKER_REGISTRY=192.168.0.10:5000

curl -s -XPOST http://$TARGET_SERVER_DOCKER_TCP/images/create?fromImage=$DOCKER_REGISTRY/mallen/docker-webapp
curl -X POST -H "Content-Type: application/json" http://$TARGET_SERVER_DOCKER_TCP/containers/create -d '{
     "Hostname":"",
     "User":"",
     "Memory":0,
     "MemorySwap":0,
     "AttachStdin":false,
     "AttachStdout":true,
     "AttachStderr":true,
     "PortSpecs":null,
     "Privileged": false,
     "Tty":false,
     "OpenStdin":false,
     "StdinOnce":false,
     "Env":null,
     "Dns":null,
     "Image":"mallen/docker-webapp",
     "Volumes":{},
     "VolumesFrom":"",
     "WorkingDir":""
}'
{"Id":"d1fa1b772481441228b93a26cc7c384a9a08cb79ea5516f101faa89564e9c752"}

