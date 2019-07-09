package com.nearsoft.commonlibrary.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;


public class Task implements Serializable {

    @Id
    public String id;
    public String name;
    public String category;

    public Task(String name, String category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%s, name='%s', category='%s']",
                id, name, category);
    }

}
