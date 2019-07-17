package com.nearsoft.service.config;

import com.nearsoft.commonlibrary.configuration.RabbitMqConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class ServerConfiguration implements RabbitListenerConfigurer {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

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

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
