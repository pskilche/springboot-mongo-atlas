package com.kiwi.springbootmongoatlas.service;

import com.kiwi.springbootmongoatlas.model.Task;
import com.kiwi.springbootmongoatlas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //CRUD CREATE, READ, UPDATE, DELETE

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskByTaskId(String taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee) {
        return taskRepository.getTaskByAssignee(assignee);
    }

    public Task updateTask(Task task) {
        //get the existing document from db
        Task existingTask = taskRepository.findById(task.getTaskId()).get();
        existingTask.setDescription(task.getDescription());
        existingTask.setSeverity(task.getSeverity());
        existingTask.setStoryPoint(task.getStoryPoint());
        existingTask.setAssignee(task.getAssignee());
        //populate new value with values from request
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        return taskId + " deleted from dashboard";
    }
}
