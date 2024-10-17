package com.raj.taskmanagement.service;

import com.raj.taskmanagement.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findByID(int id);

    Task save(Task theTask);

    void deleteById(int Id);
}
