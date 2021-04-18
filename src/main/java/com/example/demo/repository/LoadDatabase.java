package com.example.demo.repository;

import com.example.demo.model.*;

import com.example.demo.model.result.Result;
import com.example.demo.model.task.Task;
import com.example.demo.model.task.TaskType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    String initDatabase(TestRepository testRepository,
                        UserRepository userRepository,
                        TaskRepository taskRepository,
                        ResultRepository resultRepository,
                        AnswerRepository tAnswerRepository
    ) {

        //one test
        Test test = new Test("test");
        testRepository.save(test);

        //two Task
        Task task1 = new Task(TaskType.TEST, "Choose something", test );
        Task task2 = new Task(TaskType.PRACTICAL,"Select cats from table", test, "Select cats from table");
        taskRepository.save(task1);
        taskRepository.save(task2);


        //4 answers for task 1

        AnswerVariant variant1 = new AnswerVariant("Variant1",true,  task1);
        AnswerVariant variant2 = new AnswerVariant("Variant2",true,  task1);
        AnswerVariant variant3 = new AnswerVariant("Variant3",false,  task1);
        AnswerVariant variant4 = new AnswerVariant("Variant4",false,  task1);

        tAnswerRepository.save(variant1);
        tAnswerRepository.save(variant2);
        tAnswerRepository.save(variant3);
        tAnswerRepository.save(variant4);

        //1 answer for task 2

        AnswerVariant answer = new AnswerVariant("Select * from",true,  task2);
        tAnswerRepository.save(answer);

        //1 user
        User user1 = new User("Иванов", "Петр", "STUDENT", "student");
        User user2 = new User("Иванов", "Петр", "ADMIN", "admin");
        userRepository.save(user1);
        userRepository.save(user2);

//        1 Result
        Result Result = new Result(user1,task1,test,"lalal",true);
        resultRepository.save(Result);

        return "success";
    }
}
