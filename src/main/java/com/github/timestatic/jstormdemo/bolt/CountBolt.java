package com.github.timestatic.jstormdemo.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.github.timestatic.jstormdemo.common.Constants.WORD_FIELD;

/**
 * @Author: jiangqian
 * @Date: 2019/8/9 9:58
 */
@Slf4j
public class CountBolt implements IRichBolt {
    private static final long serialVersionUID = -5771147480957272344L;

    private Map<String, AtomicLong> countMap;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        countMap = new ConcurrentHashMap<>();
        log.info("count bolt prepare execute ...." + Thread.currentThread().getName());
    }

    @Override
    public void execute(Tuple input) {
        log.info("[count bolt] receive {}", input.getValues());
        String value = input.getValueByField(WORD_FIELD).toString();
        countMap.computeIfAbsent(value, k -> new AtomicLong(0)).incrementAndGet();
        log.info("[count bolt] count result, {}", countMap);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
