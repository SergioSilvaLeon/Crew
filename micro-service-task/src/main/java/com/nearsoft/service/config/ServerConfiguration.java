package com.nearsoft.service.config;

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {


    @Bean
    public Queue queueCreateTask() {
        return new Queue(RabbitMqConfig.QUEUE_CREATE_TASK);
    }

    @Bean
    public Queue queueGetTasks() {
        return new Queue(RabbitMqConfig.QUEUE_GET_TASKS);
    }

    @Bean
    public Queue queueFindTaskById() {
        return new Queue(RabbitMqConfig.QUEUE_GET_TASK_BY_ID);
    }

    @Bean
    public Queue queueDeleteTask() {
        return new Queue(RabbitMqConfig.QUEUE_DELETE_TASK);
    }

    @Bean
    public TopicExchange appExchangeNameTask() {
        return new TopicExchange(RabbitMqConfig.EXCHANGE_NAME_TASK);
    }

    @Bean
    public TopicExchange appExchangeFindTaskById() {
        return new TopicExchange(RabbitMqConfig.EXCHANGE_FIND_TASK_BY_ID);
    }

    @Bean
    public Binding declareBindingTaskCreate() {
        return BindingBuilder.bind(queueCreateTask()).to(appExchangeNameTask()).with(RabbitMqConfig.ROUTING_KEY_TASK);
    }

    @Bean
    public Binding declareBindingTaskGet() {
        return BindingBuilder.bind(queueGetTasks()).to(appExchangeNameTask()).with(RabbitMqConfig.ROUTING_KEY_TASK);
    }

    @Bean
    public Binding declareBindingTaskFindByID() {
        return BindingBuilder.bind(queueFindTaskById()).to(appExchangeFindTaskById()).with(RabbitMqConfig.ROUTING_KEY_TASK_BY_ID);
    }

    @Bean
    public Binding declareBindingTaskDelete() {
        return BindingBuilder.bind(queueDeleteTask()).to(appExchangeNameTask()).with(RabbitMqConfig.ROUTING_KEY_TASK);
    }

}
