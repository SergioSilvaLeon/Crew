package com.nearsoft.dao;

import com.nearsoft.commonlibrary.model.Task;

import java.util.List;

public interface TaskRepository {

    void saveTask(Task task);

    List<Task> getTasks();

    Task findById(String id);

    void delete(Task task);

}
