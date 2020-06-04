package com.example.demo.service;


import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.exception.TestNotFoundException;
import com.example.demo.model.task.Task;
import com.example.demo.model.Test;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private TestRepository testRepository;

    private TaskService TaskService;

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setTaskService(TaskService TaskService) {
        this.TaskService = TaskService;
    }

    @Transactional
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    @Transactional
    public  List<Test> getAll() {
        List<Test> testEntities = testRepository.findAll();
        return testEntities;
    }

    @Transactional
    public Test getById(Long id) {
        Optional<Test> test = testRepository.findById(id);
        return  test.orElseThrow(() -> new TestNotFoundException(id));
    }


    @Transactional
    public Test createTest(Test newTest) {

        if (newTest.getTestId() != null) updateTest(newTest,newTest.getTestId());

        newTest = testRepository.save(newTest);

        List<Task> tasks = newTest.getTasks();

        if (tasks != null)
            for(Task task:tasks){
                task.setTest(newTest);
                TaskService.createTask(task);
            }

        return newTest;
    }

    @Transactional
    public Test updateTest(Test newTest, Long id) {
        Test updatedTest = testRepository.findById(id)
                .map(test -> {
                    test.setName(newTest.getName());
                    List<Task> tasks = newTest.getTasks();
                    if (tasks != null)
                        for(Task task:tasks){
                            TaskService.updateTask(task,task.getTaskId());
                        }
                    return testRepository.save(test);
                })
                .orElseGet(() -> {

                    newTest.setTestId(id);
                    return testRepository.save(newTest);
                });
        return updatedTest;
    }
}
