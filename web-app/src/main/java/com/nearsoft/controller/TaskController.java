package com.nearsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TaskController {

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("productCreate", "product", new Product());
    }

}
