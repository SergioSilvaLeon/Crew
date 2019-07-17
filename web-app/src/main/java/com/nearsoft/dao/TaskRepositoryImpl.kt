package com.nearsoft.dao

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig
import com.nearsoft.commonlibrary.model.Task
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class TaskRepositoryImpl(private val mRabbitTemplate: RabbitTemplate) : TaskRepository {

    private val mLogger = LoggerFactory.getLogger(this::class.java)

    override val tasks: List<Task>
        get() = mRabbitTemplate.convertSendAndReceive(
                RabbitMqConfig.EXCHANGE_NAME_TASK,
                RabbitMqConfig.ROUTING_KEY_TASK,
                ""
        ) as List<Task>

    override fun saveTask(task: Task) {
        mLogger.info("[x] Save Task Sent {} ", task)
        mRabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_CREATE_TASK, task)
    }

    override fun findById(id: String): Task {
        val response = mRabbitTemplate.convertSendAndReceive(
                RabbitMqConfig.EXCHANGE_FIND_TASK_BY_ID,
                RabbitMqConfig.ROUTING_KEY_TASK_BY_ID,
                id
        ) as Task
        mLogger.info("[x] Recieved response from findByID {}", response)
        return response
    }

    override fun delete(task: Task) {
        mLogger.info("[x] Delete Task Sent {} ", task)
        mRabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_DELETE_TASK, task)
    }


}