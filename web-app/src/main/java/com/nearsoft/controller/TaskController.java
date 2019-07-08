package com.nearsoft.controller;

import com.nearsoft.dao.TodoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    private final TodoDAO mDao;

    public TaskController(TodoDAO dao) {
        mDao = dao;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        mDao.addTask("holis");
        model.addAttribute("name", name);
        return "greeting";
    }

}
