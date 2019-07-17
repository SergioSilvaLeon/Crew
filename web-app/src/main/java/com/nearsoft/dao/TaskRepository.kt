package com.nearsoft.dao

import com.nearsoft.commonlibrary.model.Task

interface TaskRepository {

    val tasks: List<Task>

    fun saveTask(task: Task)

    fun findById(id: String): Task

    fun delete(task: Task)

}
