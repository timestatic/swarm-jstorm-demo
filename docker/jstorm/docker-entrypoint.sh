#!/bin/sh

set -e

if [ ! -f "$JSTORM_HOME/conf/storm.yaml" ]; then
    CONFIG="$JSTORM_HOME/conf/storm.yaml"
    echo "storm.zookeeper.servers: [$storm_zookeeper_servers]" >> "$CONFIG"
    echo "nimbus.seeds: [$nimbus_seeds]" >> "$CONFIG"
fi
echo "====== print storm.yaml ======"
echo `cat $JSTORM_HOME/conf/storm.yaml`
exec "$@"