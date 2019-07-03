package com.nearsoft.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableJms
public class TodoDAOImpl implements TodoDAO {

    private final Logger mLogger = LoggerFactory.getLogger(this.getClass());
    private JmsTemplate mJmsTemplate;

    public TodoDAOImpl(JmsTemplate jmsTemplate) {
        mJmsTemplate = jmsTemplate;
    }

    @Override
    public void addTask(String t) {
        mLogger.info("[x] Task Sent {} ", t);
        mJmsTemplate.convertAndSend("mailbox", "Get Crayons");
    }

    @Override
    public List<String> getTasks() {
        // TODO: Get tasks
        return null;
    }
}