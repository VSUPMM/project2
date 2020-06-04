package com.example.demo.model.result;

import com.example.demo.model.Test;
import com.example.demo.model.User;

import com.example.demo.model.embeddedId.ResultId;
import com.example.demo.model.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "task_result")
public class Result {

    @Id
    @EmbeddedId
    private ResultId id;

    @JsonBackReference(value="UserResult-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("user")
    @JoinColumn(name = "fk_user")
    private User user;

    @JsonBackReference(value="TaskResult-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("task")
    @JoinColumn(name = "fk_task")
    private Task task;

    @JsonBackReference(value="TestResult-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "text")
    private String answerText;  //todo

    @Column
    private Boolean correct;

    public Result() {

    }

    public Result(User user, Task task, Test test, String answerText, Boolean correct) {
       id = new ResultId(task.getTaskId(),user.getUserId());
        this.user = user;
        this.task = task;
        this.test = test;
        this.answerText = answerText;
        this.correct = correct;
    }

}

