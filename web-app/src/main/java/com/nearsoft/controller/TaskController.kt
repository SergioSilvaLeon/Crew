package com.nearsoft.controller

import com.nearsoft.commonlibrary.model.Task
import com.nearsoft.dao.TaskRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

import javax.validation.Valid

@Controller
class TaskController(private val respository: TaskRepository) {

    @GetMapping("/addnewentery")
    fun showSignUpForm(task: Task): String {
        return "add-task"
    }

    @PostMapping("/addtask")
    fun addTask(task: Task, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "add-task"
        }
        respository.saveTask(task)
        model.addAttribute("tasks", respository.tasks)
        return "index"
    }

    @GetMapping("/edit/{id}")
    fun showUpdateForm(@PathVariable("id") id: String, model: Model): String {
        val task = respository.findById(id)
        model.addAttribute("task", task)
        return "update-task"
    }

    @PostMapping("/update/{id}")
    fun updateUser(@PathVariable("id") id: String, @Valid task: Task, result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            task.setId(id)
            return "update-task"
        }

        respository.saveTask(task)
        model.addAttribute("tasks", respository.tasks)
        return "index"
    }

    @GetMapping("/delete/{id}")
    fun deleteUser(@PathVariable("id") id: String, model: Model): String {
        val task = respository.findById(id)
        respository.delete(task)
        model.addAttribute("tasks", respository.tasks)
        return "index"
    }

}
