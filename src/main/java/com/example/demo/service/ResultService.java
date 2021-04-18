package com.example.demo.service;

import com.example.demo.model.dto.pages.ResultTo;
import com.example.demo.model.dto.pages.TestResultTO;
import com.example.demo.model.dto.pages.UserResultTO;
import com.example.demo.exception.AnswerNotFoundException;

import com.example.demo.exception.ResultNotFoundException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.*;

import com.example.demo.model.embeddedId.ResultId;
import com.example.demo.model.result.Result;
import com.example.demo.model.task.Task;
import com.example.demo.model.task.TaskType;
import com.example.demo.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ResultService {

    ResultRepository resultRepository;

    private TaskService taskService;

    private UserService userService;

    private TestService testService;

    @Autowired
    public void setService(TestService service) {
        this.testService = service;
    }

    @Autowired
    public void setService(UserService service) {
        this.userService = service;
    }

    @Autowired
    public void setService(TaskService service) {
        this.taskService = service;
    }

    @Autowired
    public void setRepository(ResultRepository repository) {
        this.resultRepository = repository;
    }


    @Transactional
    public void deleteResult(Long userId, Long taskId) {
        resultRepository.deleteById(new ResultId(taskId,userId));
    }

    @Transactional
    public List<Result> getAll() {
        List<Result> resultEntities = resultRepository.findAll();
        return resultEntities;
    }

    @Transactional
    public Result getById(Long userId, Long taskId) {
        Optional<Result> Result = resultRepository.findById(new ResultId(taskId,userId));
        return  Result.orElseThrow(() -> new ResultNotFoundException(userId,taskId));
    }

    @Transactional
    public Result createResult(Long userId, Long taskId, Result Result) {

        Task task = taskService.getById(taskId);

        User user = userService.getById(userId);

        //Test test = testService.getById(result.get);

        Boolean correct = checkAnswer(task , Result.getAnswerText());

        Result savedResult = resultRepository.save(new Result(user, task , Result.getTest(), Result.getAnswerText(), correct));

        return savedResult;
    }


    @Transactional
    public Result createResult(Long userId, ResultTo resultTO) {
        
        Task task = taskService.getById(resultTO.getTaskId());

        User user = userService.getById(userId);

        Test test = testService.getById(task.getTest().getTestId());

        Boolean correct = checkAnswer(task , resultTO.getAnswer());

        Result savedResult = new Result( user,  task , test, resultTO.getAnswer() , correct);
        savedResult = resultRepository.save(savedResult);
        return savedResult;

    }


    @Transactional
    public Result updateResult(Result newResult, Long userId, ResultTo resultTO) {

        Result updatedResult = resultRepository.findById(new ResultId(resultTO.getTaskId(),userId))
                .map(Result -> {
                    Result.setAnswerText(resultTO.getAnswer());
                    Task task = taskService.getById(resultTO.getTaskId());
                    Boolean correct = checkAnswer(task , resultTO.getAnswer());
                    Result.setCorrect(correct);
                    return resultRepository.save(Result);
                })
                .orElseGet(() -> {
                    newResult.setId(new ResultId(resultTO.getTaskId(),userId));
                    return resultRepository.save(newResult);
                });

        return updatedResult;
    }
    
    /*
    1. Определяем тип задания
    2. Ищем ответ на задание
    3. Сравниваем ответ с userAnswer
     */
    private boolean checkAnswer (Task task, String userAnswer) {

        String rightAnswer = "";

        if (task.getTaskType() == TaskType.TEST) {

            List<AnswerVariant> answers = task.getPossibleAnswers();

            if (answers != null){

                for (AnswerVariant answer : answers) {
                    if (answer.getCorrect()) rightAnswer = answer.getText();
                }
                return userAnswer.equals(rightAnswer);
            }
            else throw new AnswerNotFoundException();
        }
   /*     if (task.getTaskType() == TaskType.PRACTICAL) {

            rightAnswer = task.getAnswerText();

            if (rightAnswer != null) {
                return toFormatQuery(rightAnswer).equals(toFormatQuery(userAnswer));
            }
            else throw new AnswerNotFoundException();
        }*/

        else throw new TaskNotFoundException(task.getTaskId());
    }


    /*
   1. Удаляем пробелы
   2. Удаляем переносы строк
   3. Приводим к верхнему регистру
    */
    private String toFormatQuery (String query) {
        query = query.toUpperCase();
        query = query.trim().replaceAll(" +", " ");
        query = query.replaceAll("\\s+", " ");
        return query;
    }

    @Transactional
    public List<TestResultTO> getTestStatistics (Long testId) {

        Test test = testService.getById(testId);
        List<TestResultTO> testResultTOs = new ArrayList<>();
        List<User> users = userService.getAll();
        for(User user : users){
            List<Result> results = resultRepository.findByUserAndTest(user, test);

            if (results.size() == 0)
                testResultTOs.add(new TestResultTO(user.getName(),user.getSurname()));
            else
                testResultTOs.add(new TestResultTO(user.getName(),user.getSurname(),
                        (int)(results.stream().filter(Result::getCorrect).count())/results.size()*100));
        }
        return testResultTOs;
    }

    @Transactional
    public List<UserResultTO> getUserStatistics (Long userId) {

        User user = userService.getById(userId);

        List<Test> tests = testService.getAll();

        List<UserResultTO> userResultTOs = new ArrayList<>();

        for(Test test : tests){
            List<Result> results = resultRepository.findByUserAndTest(user, test);

            if (results.size() == 0)
                userResultTOs.add(new UserResultTO(test.getTestId(), test.getName()));
            else
                userResultTOs.add(new UserResultTO(test.getTestId(), test.getName(),
                        (int) (results.stream().filter(Result::getCorrect).count())/results.size()*100));
        }

        return userResultTOs;

    }



}
