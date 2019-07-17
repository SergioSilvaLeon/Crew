package com.nearsoft.service.repository

import com.nearsoft.commonlibrary.model.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, String>


