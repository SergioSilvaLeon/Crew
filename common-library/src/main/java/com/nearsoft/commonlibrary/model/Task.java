package com.nearsoft.commonlibrary.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Task implements Serializable {

    @Id
    public String id;
    public String name;
    public String category;

    public Task() {
        super();
    }

    public Task(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Task(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%s, name='%s', category='%s']",
                id, name, category);
    }

}
