package com.nearsoft.dao;

import java.util.List;

public interface TodoDAO {

    void addTask(String t);

    List<String> getTasks();

}
