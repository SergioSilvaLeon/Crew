package com.nearsoft.controller;

import com.nearsoft.commonlibrary.model.Task;
import com.nearsoft.dao.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class TaskController {

    private final TaskRepository respository;

    public TaskController(TaskRepository dao) {
        respository = dao;
    }

    @GetMapping("/task")
    public String showSignUpForm(Task task) {
        return "add-task";
    }

    @PostMapping("/addTask")
    public String addTask(Task task, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-task";
        }
        respository.saveTask(task);
        model.addAttribute("tasks", respository.getTasks());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Task task = respository.findById(id);

        model.addAttribute("task", task);
        return "update-task";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id, @Valid Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-task";
        }

        respository.saveTask(task);
        model.addAttribute("tasks", respository.getTasks());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        Task task = respository.findById(id);
        respository.delete(task);
        model.addAttribute("users", respository.getTasks());
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
