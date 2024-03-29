package com.kiwi.springbootmongoatlas.repository;

import com.kiwi.springbootmongoatlas.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findBySeverity(int severity);

    //trying not to use default method
    @Query("{ assignee: ?0 }")
    List<Task> getTaskByAssignee(String assignee);
}
