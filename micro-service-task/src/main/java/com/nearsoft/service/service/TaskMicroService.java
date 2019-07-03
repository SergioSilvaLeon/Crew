package com.nearsoft.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMicroService {

    private final Logger mLogger = LoggerFactory.getLogger(TaskMicroService.class);

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void createTask(String task) {
        mLogger.info("[x] Received task {}", task);

        // TODO: Insert task into repostory
    }

    public List<String> getTasks() {
        mLogger.info("[x] Received  Get Tasks Request");

        return null;
    }

}
