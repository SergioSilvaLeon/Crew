package com.nearsoft.dao;

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig;
import com.nearsoft.commonlibrary.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepositoryImpl implements TaskRepository {

    private final Logger mLogger = LoggerFactory.getLogger(this.getClass());
    private RabbitTemplate mRabbitTemplate;

    public TaskRepositoryImpl(RabbitTemplate rabbitTemplate) {
        mRabbitTemplate = rabbitTemplate;
    }

    @Override
    public void saveTask(Task task) {
        mLogger.info("[x] Save Task Sent {} ", task);
        mRabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_CREATE_TASK, task);
    }

    @Override
    public List<Task> getTasks() {
        List<Task> response =
                (List<Task>) mRabbitTemplate.convertSendAndReceive(
                        RabbitMqConfig.EXCHANGE_NAME_TASK,
                        RabbitMqConfig.ROUTING_KEY_TASK,
                        ""
                );
        return response;
    }

    @Override
    public Task findById(String id) {
        Task response = (Task) mRabbitTemplate.convertSendAndReceive(
                RabbitMqConfig.EXCHANGE_FIND_TASK_BY_ID,
                RabbitMqConfig.ROUTING_KEY_TASK_BY_ID,
                id
        );
        mLogger.info("[x] Recieved response from findByID {}", response);
        return response;
    }

    @Override
    public void delete(Task task) {
        mLogger.info("[x] Delete Task Sent {} ", task);
        mRabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_DELETE_TASK, task);
    }


}