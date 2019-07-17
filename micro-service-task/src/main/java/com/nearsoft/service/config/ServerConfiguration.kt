package com.nearsoft.service.config

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ServerConfiguration {


    @Bean
    open fun queueCreateTask(): Queue {
        return Queue(RabbitMqConfig.QUEUE_CREATE_TASK)
    }

    @Bean
    open fun queueGetTasks(): Queue {
        return Queue(RabbitMqConfig.QUEUE_GET_TASKS)
    }

    @Bean
    open fun queueFindTaskById(): Queue {
        return Queue(RabbitMqConfig.QUEUE_GET_TASK_BY_ID)
    }

    @Bean
    open fun queueDeleteTask(): Queue {
        return Queue(RabbitMqConfig.QUEUE_DELETE_TASK)
    }

    @Bean
    open fun appExchangeNameTask(): TopicExchange {
        return TopicExchange(RabbitMqConfig.EXCHANGE_NAME_TASK)
    }

    @Bean
    open fun appExchangeFindTaskById(): TopicExchange {
        return TopicExchange(RabbitMqConfig.EXCHANGE_FIND_TASK_BY_ID)
    }

    @Bean
    open fun declareBindingTaskCreate(): Binding {
        return BindingBuilder.bind(queueCreateTask()).to(appExchangeNameTask()).with(RabbitMqConfig.ROUTING_KEY_TASK)
    }

    @Bean
    open fun declareBindingTaskGet(): Binding {
        return BindingBuilder.bind(queueGetTasks()).to(appExchangeNameTask()).with(RabbitMqConfig.ROUTING_KEY_TASK)
    }

    @Bean
    open fun declareBindingTaskFindByID(): Binding {
        return BindingBuilder.bind(queueFindTaskById()).to(appExchangeFindTaskById()).with(RabbitMqConfig.ROUTING_KEY_TASK_BY_ID)
    }

    @Bean
    open fun declareBindingTaskDelete(): Binding {
        return BindingBuilder.bind(queueDeleteTask()).to(appExchangeNameTask()).with(RabbitMqConfig.ROUTING_KEY_TASK)
    }

}
