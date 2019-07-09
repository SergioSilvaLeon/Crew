package com.nearsoft.service.service;

import com.nearsoft.commonlibrary.configuration.ActiveMqConfig;
import com.nearsoft.commonlibrary.model.Task;
import com.nearsoft.service.repository.TaskRepository;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.List;

@Service
public class TaskMicroService implements SessionAwareMessageListener<Message> {

    private final Logger mLogger = LoggerFactory.getLogger(TaskMicroService.class);
    private TaskRepository mTaskRepository;

    public TaskMicroService(TaskRepository mTaskRepository) {
        this.mTaskRepository = mTaskRepository;
    }

    @JmsListener(destination = ActiveMqConfig.QUEUE_CREATE_TASK)
    public void saveTask(Task task) {
        mLogger.info("[x] Received task {}", task);
        mTaskRepository.save(task);
    }

    @JmsListener(destination = ActiveMqConfig.QUEUE_GET_TASK)
    public List<Task> getTasks() {
        mLogger.info("[x] Received  Get Tasks Request");
        return mTaskRepository.findAll();
    }

//    @JmsListener(destination = ActiveMqConfig.QUEUE_GET_TASK_BY_ID)
//    public Optional<Task> getTaskById(Task task) {
//        return mTaskRepository.findById(task.id);
//    }

    @JmsListener(destination = ActiveMqConfig.QUEUE_DELETE_TASK)
    public void deleteTask(Task task) {
        mTaskRepository.delete(task);
    }

    @JmsListener(destination = ActiveMqConfig.QUEUE_GET_TASK_BY_ID)
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        String id = (String) ((ActiveMQObjectMessage) message).getObject();
//        Optional<Task> task = mTaskRepository.findById(String.valueOf(id));
        Task task = new Task("homework", "school");

        // done handling the request, now create a response message
        final ObjectMessage responseMessage = new ActiveMQObjectMessage();
        responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
        responseMessage.setObject(task);

        // Message sent back to the replyTo address of the income message.
        final MessageProducer producer = session.createProducer(message.getJMSReplyTo());
        producer.send(responseMessage);
    }
}
