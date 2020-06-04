//package com.example.demo.model.embeddedId;
//
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import java.io.Serializable;
//
//
//@Embeddable
//public class TaskResultId implements Serializable {
//
//    @Column(name = "Result")
//    private ResultId resultId;
//
//    @Column(name = "task")
//    private Long taskId;
//
//    public TaskResultId(
//            ResultId resultId, Long taskId
//    ) {
//        this.resultId = resultId;
//        this.taskId = taskId;
//    }
//
//
//    public ResultId getresultId() {
//        return resultId;
//    }
//
//    public Long gettaskId() {
//        return taskId;
//    }
//
//}