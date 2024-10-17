package com.raj.taskmanagement.service;

import com.raj.taskmanagement.dao.TaskRepository;
import com.raj.taskmanagement.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;


    @PersistenceContext
    private EntityManager entityManager;

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
//    @Transactional
    public void deleteById(int Id) {
        taskRepository.deleteById(Id);
//        entityManager.joinTransaction();
        int query = entityManager.createNativeQuery("SELECT setval(pg_get_serial_sequence('tasks', 'id'), COALESCE(MAX(id), 0)) FROM tasks").executeUpdate();
    }

//    @Transactional
//    protected void resetSequence() {
//        // Set the sequence to continue from the last used ID
//        entityManager.joinTransaction();
//        entityManager.createNativeQuery("SELECT setval(pg_get_serial_sequence('tasks', 'id'), COALESCE(MAX(id), 0)) FROM tasks")
//                .executeUpdate();
//    }
}
