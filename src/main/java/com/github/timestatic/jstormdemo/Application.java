package com.github.timestatic.jstormdemo;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.utils.Utils;
import com.github.timestatic.jstormdemo.topology.TopologyStart;
import lombok.extern.slf4j.Slf4j;

import static com.github.timestatic.jstormdemo.common.Constants.TOPOLOGY_NAME;

/**
 * @Author: jiangqian
 * @Date: 2019/8/9 10:08
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        try {
            clusterRun();
            System.out.println("topology submit success");
            Thread.currentThread().join();
        } catch (Exception e) {
            log.error("submit topology error", e);
        }
    }

    private static void clusterRun() throws AlreadyAliveException, InvalidTopologyException {
        Config config = new Config();
        config.setNumWorkers(3);
        config.setDebug(false);
        StormSubmitter.submitTopology(TOPOLOGY_NAME, config, TopologyStart.create());
    }

    private static void localRun() {
        Config config = new Config();
        config.setDebug(true);
        config.setNumWorkers(1);
        config.setMaxSpoutPending(200);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(TOPOLOGY_NAME, config, TopologyStart.create());
        Utils.sleep(10000000000L);
        cluster.shutdown();
    }

}
