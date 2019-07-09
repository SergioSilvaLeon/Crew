package com.nearsoft.service.repository;

import com.nearsoft.commonlibrary.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    public List<Task> findByLastName(String lastName);

}


