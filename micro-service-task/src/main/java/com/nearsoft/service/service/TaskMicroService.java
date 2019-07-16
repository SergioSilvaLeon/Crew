package com.nearsoft.service.service;

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig;
import com.nearsoft.commonlibrary.model.Task;
import com.nearsoft.service.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMicroService {

    private final Logger mLogger = LoggerFactory.getLogger(TaskMicroService.class);
    private TaskRepository mTaskRepository;
    // TODO: Add queue names staticallly

    public TaskMicroService(TaskRepository taskRepository) {
        mTaskRepository = taskRepository;
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_CREATE_TASK)
    public void saveTask(Task task) {
        mLogger.info("[x] Received task {}", task);
        mTaskRepository.save(task);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_GET_TASKS)
    public List<Task> getTasks() {
        mLogger.info("[x] Received  Get Tasks Request");
        return mTaskRepository.findAll();
    }

    public void deleteTask(Task task) {
        mTaskRepository.delete(task);
    }


}
