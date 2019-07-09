package com.nearsoft.dao;

import com.nearsoft.commonlibrary.configuration.ActiveMqConfig;
import com.nearsoft.commonlibrary.model.Task;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.List;
import java.util.UUID;

@Component
@EnableJms
public class TaskRepositoryImpl implements TaskRepository {

    private final Logger mLogger = LoggerFactory.getLogger(this.getClass());
    private JmsTemplate jmsTemplate;
    private JmsMessagingTemplate jmsMessagingTemplate;

    public TaskRepositoryImpl(JmsTemplate jmsTemplate, JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @Override
    public void saveTask(Task task) {
        mLogger.info("[x] Save Task Sent {} ", task);
        // TODO: Set ID automatically
        task.setId(2L);
        jmsTemplate.convertAndSend(ActiveMqConfig.QUEUE_CREATE_TASK, task);
    }

    @Override
    public List<Task> getTasks() {
        mLogger.info("[x] Retrieve Tasks Sent {} ");
        return null;
    }

    @Override
    public Task findById(long id) throws JMSException {
        jmsTemplate.setReceiveTimeout(1000L);
        jmsMessagingTemplate.setJmsTemplate(jmsTemplate);

        Session session = jmsMessagingTemplate.getConnectionFactory().createConnection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE);

        ObjectMessage objectMessage = session.createObjectMessage(id);

        objectMessage.setJMSCorrelationID(UUID.randomUUID().toString());
        objectMessage.setJMSReplyTo(new ActiveMQQueue(ActiveMqConfig.QUEUE_GET_TASK_BY_ID_REPLY));
        objectMessage.setJMSCorrelationID(UUID.randomUUID().toString());
        objectMessage.setJMSExpiration(1000L);
        objectMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

        return jmsMessagingTemplate.convertSendAndReceive(new ActiveMQQueue(ActiveMqConfig.QUEUE_GET_TASK_BY_ID),
                objectMessage, Task.class); //this operation seems to be blocking + sync
    }

    @Override
    public void delete(Task task) {
        mLogger.info("[x] Save Task Sent {} ", task);
        jmsTemplate.convertAndSend(ActiveMqConfig.QUEUE_DELETE_TASK, task);
    }


}