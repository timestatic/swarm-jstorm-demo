#!/bin/bash

set -e

if [ ! -f "~/.jstorm/storm.yaml" ]; then
    mkdir -p ~/.jstorm

    CONFIG="/tmp/storm.yaml"
    touch "$CONFIG"

    echo -e "storm.zookeeper.servers: [$storm_zookeeper_servers]" >> "$CONFIG"
    echo -e "nimbus.seeds: [$nimbus_seeds]" >> "$CONFIG"
    echo -e "ui.clusters:
                - {
                    name: ${cluster_name:-'jstorm.share'},
                    zkRoot: ${zkRoot:-'/jstorm'},
                    zkServers: [$storm_zookeeper_servers],
                    zkPort: ${zkRoot:-2181},
                  }" >> "$CONFIG"

   mv $CONFIG ~/.jstorm

fi

exec "$@"
