Docker Swarm 

build jstorm image
```shell
docker build -t 
```

build jstorm-ui image
```shell
docker build -t 
```

build demo-app image
```shell
mvn package assembly:single
```
```shell
docker build -t 
```

start up jstorm nimbus, supervisor and ui
```shell
docker stack deploy -c jstorm-docker-compose.yml jstormswarm
```