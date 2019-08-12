JStorm docker image and JStorm cluster based on Docker Swarm 

start up jstorm nimbus, supervisor and ui
```shell
docker pull timestatic/jstorm-ui:2.2.1
docker pull timestatic/jstorm:2.2.1

# pre-create volume directory in yaml

docker network create --driver overlay net_swarm_test
docker stack deploy -c jstorm-docker-compose.yml jstormswarm
```

submit demo topology
```shell
 docker stack deploy -c demo.yaml jstorm
```

build demo-app
```shell
mvn package assembly:single
```
