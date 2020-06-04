package com.example.demo.model.task;

import com.example.demo.model.AnswerVariant;
import com.example.demo.model.Test;
import com.example.demo.model.result.Result;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private  Long  taskId;

    @Column (name = "type")
    private TaskType taskType;

    @Column
    private String question;

    @Column
    private String answerText;

    @JsonBackReference(value="Test-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name ="test_id")
    private Test test;

    @JsonManagedReference(value="AnswerVariant-movement")
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy="fkTask", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<AnswerVariant> possibleAnswers ;

    @JsonManagedReference(value="TaskResult-movement")
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy="task", cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
    private List<Result> taskResults;

    public Task() {
    }

    public Task(TaskType taskType, String question, Test test) {
        this.taskType = taskType;
        this.question = question;
        this.test = test;
    }

    public Task(TaskType taskType, String question,  Test test, String text) {
        this.taskType = taskType;
        this.question = question;
        this.test = test;
        this.answerText = text;
    }

}

