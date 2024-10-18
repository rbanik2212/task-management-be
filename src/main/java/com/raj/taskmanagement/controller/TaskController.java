package com.raj.taskmanagement.controller;


import com.raj.taskmanagement.entity.Task;
import com.raj.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    public List<Task> findAll() {
        return taskService.findAll();
    }


    @GetMapping("/tasks/{id}")
    public Task findById(@PathVariable int id) {
        Task theTask = taskService.findByID(id);
        if (theTask == null) {
            throw new RuntimeException("task not found - " + id);
        }
        return theTask;
    }


    @PostMapping("/task")
    public Task addTask(@RequestBody Task theTask){
        theTask.setId(0);
        Task dbTask = taskService.save(theTask);
        return dbTask;
    }


    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable int id){
        taskService.deleteById(id);

        return "deleted";
    }
}