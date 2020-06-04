package com.example.demo.model.embeddedId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResultId implements Serializable {

    @Column(name = "fk_user")
    private Long userId;

    @Column(name = "fk_task")
    private Long taskId;

    private ResultId() {}

    public ResultId(
            Long taskId, Long userId
    ) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public Long getuserId() {
        return userId;
    }

    public Long gettaskId() {
        return taskId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }


}