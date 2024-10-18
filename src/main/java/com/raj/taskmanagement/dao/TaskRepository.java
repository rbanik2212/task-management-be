package com.raj.taskmanagement.dao;

import com.raj.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT setval(pg_get_serial_sequence('tasks', 'id'), COALESCE(MAX(id), 0)) FROM tasks", nativeQuery = true)
    Integer resetTaskIdSequence();
}
