package com.github.timestatic.jstormdemo.topology;

import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import com.github.timestatic.jstormdemo.bolt.CountBolt;
import com.github.timestatic.jstormdemo.spout.WordSpout;

/**
 * @Author: jiangqian
 * @Date: 2019/8/9 10:19
 */
public class TopologyStart {

    public static StormTopology create() {
        String spoutId = WordSpout.class.getSimpleName();
        String boltId = CountBolt.class.getSimpleName();
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout(spoutId, new WordSpout(), 2);
        topologyBuilder.setBolt(boltId, new CountBolt(), 4).localOrShuffleGrouping(spoutId);
        return topologyBuilder.createTopology();
    }


}
