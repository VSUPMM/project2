package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.AnswerVariant;
import com.example.demo.model.task.Task;

import com.example.demo.repository.TaskRepository;

import com.example.demo.repository.TestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    @Autowired
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    private UserRepository userRepository;

    @Autowired
    public void setRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private TestRepository testRepository;

    @Autowired
    public void setRepository(TestRepository repository) {
        this.testRepository = repository;
    }

    private AnswerService answerSevice;

    @Autowired
    public void setAnswerSevice(AnswerService answerSevice) {
        this.answerSevice = answerSevice;
    }

    private ResultService resultSevice;

    @Autowired
    public void setResultSevice(ResultService resultSevice) {
        this.resultSevice = resultSevice;
    }

    @Transactional
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<Task> getAll() {
        List<Task> taskEntities = repository.findAll();
        return taskEntities;
    }

    @Transactional
    public Task getById(Long id) {
        Optional<Task> task = repository.findById(id);
        return  task.orElseThrow(() -> new TaskNotFoundException(id));

    }

    @Transactional
    public Task createTask(Task newTask) {

        newTask = repository.save(newTask);

        List<AnswerVariant> answers = newTask.getPossibleAnswers();

        if (answers != null)
            for(AnswerVariant answer :answers){
                answer.setFkTask(newTask);
                answerSevice.createAnswer(answer);
            }

        return newTask;
    }

    @Transactional
    public Task updateTask(Task newTask, Long id) {

        Task updatedTask = repository.findById(id)
                .map(task -> {
                    task.setQuestion(newTask.getQuestion());
                    List<AnswerVariant> answers = newTask.getPossibleAnswers();
                    if (answers != null)
                    for(AnswerVariant answer :answers){
                        answerSevice.updateAnswer(answer,answer.getAnswerId());
                    }
                    return repository.save(task);
                })
                .orElseGet(() -> {
                    newTask.setTaskId(id);
                    return repository.save(newTask);
                });

        return updatedTask;
    }


    /*1. Находим пользователя, присваиваем его результату
     2. Проходим по списку заданий
     3. Каждое задание ищем и присваиваем его результату
     5. Каждый тест ищем и присваиваем его результату
     6. Передаем всё сервису результата, но с userId*/
    /*public List<Task> saveResult ( List<Task> tasks , Long userId){

        Optional<User> user = userRepository.findById(userId);

        for(Task task :tasks) {

            List<Result> results = task.getTaskResults();

            if (results != null)
                for (Result result : results) {

                    Optional<Task> taskSaved = repository.findById(task.getTaskId());
                    taskSaved.ifPresent(result::setTask);

                    Optional<Test> test = testRepository.findById(taskSaved.get().getTest().getTestId());
                    test.ifPresent(result::setTest);

                    user.ifPresent(result::setUser);

                    resultSevice.createResult(result, new ResultId(task.getTaskId(),userId));
                }
        }
        return  null;
    }*/

}
