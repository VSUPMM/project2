package com.example.demo.model;

import com.example.demo.model.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "answer_variant")
public class AnswerVariant {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "text")
    private String text;

    @Column(name = "correct")
    private Boolean correct;

    @JsonBackReference(value="AnswerVariant-movement")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name ="task_id")
    private Task fkTask;

    public AnswerVariant() {
    }

    public AnswerVariant(String text, Boolean correct, Task task) {
        this.text = text;
        this.correct = correct;
        this.fkTask = task;
    }
}
