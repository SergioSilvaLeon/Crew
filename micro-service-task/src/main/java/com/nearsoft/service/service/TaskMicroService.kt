package com.nearsoft.service.service

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig
import com.nearsoft.commonlibrary.model.Task
import com.nearsoft.service.repository.TaskRepository
import org.slf4j.LoggerFactory.getLogger
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class TaskMicroService(private val mTaskRepository: TaskRepository) {

    private val mLogger = getLogger(TaskMicroService::class.simpleName)

    val tasks: List<Task>
        @RabbitListener(queues = [RabbitMqConfig.QUEUE_GET_TASKS])
        get() {
            mLogger.info("[x] Received  Get Tasks Request")
            return mTaskRepository.findAll()
        }

    @RabbitListener(queues = [RabbitMqConfig.QUEUE_CREATE_TASK])
    fun saveTask(task: Task) {
        mLogger.info("[x] Received task {}", task)
        mTaskRepository.save(task)
    }

    @RabbitListener(queues = [RabbitMqConfig.QUEUE_GET_TASK_BY_ID])
    fun findTaskById(id: String): Task {
        mLogger.info("[x] Received Find Task by ID {}", id)
        val response = mTaskRepository.findById(id)
        return response.orElseGet { Task("404", "Not", "Found") }
    }

    @RabbitListener(queues = [RabbitMqConfig.QUEUE_DELETE_TASK])
    fun deleteTask(task: Task) {
        mLogger.info("[x] Received Delete Task")
        mTaskRepository.delete(task)
    }

}
