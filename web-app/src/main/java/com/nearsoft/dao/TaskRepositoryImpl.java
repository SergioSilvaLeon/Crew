package com.nearsoft.dao;

import com.nearsoft.commonlibrary.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableJms
public class TaskRepositoryImpl implements TaskRepository {

    private final Logger mLogger = LoggerFactory.getLogger(this.getClass());
    private JmsTemplate mJmsTemplate;

    public TaskRepositoryImpl(JmsTemplate jmsTemplate) {
        mJmsTemplate = jmsTemplate;
    }

    @Override
    public void saveTask(Task task) {
        mLogger.info("[x] Task Sent {} ", task);
        mJmsTemplate.convertAndSend("mailbox", task);
    }

    @Override
    public List<Task> getTasks() {
        // TODO: Get tasks
        return null;
    }

    @Override
    public Task findById(long id) {
        return null;
    }

    @Override
    public void delete(Task task) {

    }


}