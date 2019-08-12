JStorm docker image and JStorm cluster based on Docker Swarm 

start up jstorm nimbus, supervisor and ui
```shell
docker network create --driver overlay net_swarm_test
docker stack deploy -c jstorm-docker-compose.yml jstormswarm
```

submit demo topology
```shell
 docker stack deploy -c demo.yaml jstorm
```

build demo-app image
```shell
mvn package assembly:single
```
