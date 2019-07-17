package com.nearsoft.commonlibrary.configuration

object RabbitMqConfig {
    const val EXCHANGE_NAME_TASK = "EXCHANGE_NAME_TASK"
    const val EXCHANGE_FIND_TASK_BY_ID = "EXCHANGE_FIND_TASK_BY_ID"
    const val ROUTING_KEY_TASK = "ROUTING_KEY_TASK"
    const val ROUTING_KEY_TASK_BY_ID = "ROUTING_KEY_TASK_BY_ID"
    const val QUEUE_CREATE_TASK = "QUEUE_CREATE_TASK"
    const val QUEUE_GET_TASKS = "QUEUE_GET_TASKS"
    const val QUEUE_DELETE_TASK = "QUEUE_DELETE_TASK"
    const val QUEUE_GET_TASK_BY_ID = "QUEUE_GET_TASK_BY_ID"
}
