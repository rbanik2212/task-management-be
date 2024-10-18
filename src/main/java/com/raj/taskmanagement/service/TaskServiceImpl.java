package com.raj.taskmanagement.service;

import com.raj.taskmanagement.dao.TaskRepository;
import com.raj.taskmanagement.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }


    @Override
    public Task findByID(int Id) {
        Optional<Task> result = taskRepository.findById(Id);
        Task theTask = null;

        if (result.isPresent()) {
            theTask = result.get();
        } else {
            throw new RuntimeException("NO TASK of ID " + Id);
        }
        return theTask;
    }


    @Override
    public Task save(Task theTask) {
        return taskRepository.save(theTask);
    }


    @Override
    @Transactional
    public void deleteById(int Id) {
        taskRepository.deleteById(Id);
        taskRepository.resetTaskIdSequence();
    }
}