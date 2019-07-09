package com.nearsoft.dao;

import com.nearsoft.commonlibrary.model.Task;

import javax.jms.JMSException;
import java.util.List;

public interface TaskRepository {

    void saveTask(Task task);

    List<Task> getTasks();

    Task findById(long id) throws JMSException;

    void delete(Task task);

}
