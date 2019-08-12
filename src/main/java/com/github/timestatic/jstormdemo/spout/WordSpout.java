package com.github.timestatic.jstormdemo.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.github.timestatic.jstormdemo.common.Constants.WORD_FIELD;

/**
 * @Author: jiangqian
 * @Date: 2019/8/10 14:16
 */
@Slf4j
public class WordSpout implements IRichSpout {

    private static final long serialVersionUID = -5428340036118584840L;

    private static final String[] WORDS = {"A", "AB", "ABC", "ABCD", "ABCDE", "ABCDEF", "ABCDEFG"};
    private static final int length = WORDS.length;

    private SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        log.info("spout componentTaskIds {}", context.getComponentTasks(context.getThisComponentId()));
    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    /**
     * 发往bolt的数据
     */
    @Override
    public void nextTuple() {
        String value = WORDS[new Random().nextInt(length)];
        log.info("[word spout] create value {}", value);
        collector.emit(new Values(value));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void ack(Object msgId) {

    }

    @Override
    public void fail(Object msgId) {

    }

    /**
     * 定义发射的字段
     *
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(WORD_FIELD));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
