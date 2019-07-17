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

    @RabbitListener(queues = RabbitMqConfig.QUEUE_GET_TASK_BY_ID)
    public Task findTaskById(String id) {
        mLogger.info("[x] Received Find Task by ID {}", id);
        return mTaskRepository.findById(id).get();
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_DELETE_TASK)
    public void deleteTask(Task task) {
        mLogger.info("[x] Received Delete Task");
        mTaskRepository.deleteAll();
        mTaskRepository.delete(task);
    }

}
