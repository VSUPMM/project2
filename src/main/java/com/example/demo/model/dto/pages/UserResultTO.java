package com.example.demo.model.dto.pages;

import lombok.Data;

@Data
public class UserResultTO {

    Long testId;

    String testName;

    int percent;

    public UserResultTO(  Long testId, String testName, int percent) {
        this.testName = testName;
        this.percent = percent;
        this.testId = testId;
    }

    public UserResultTO(  Long testId, String testName) {
        this.testName = testName;
        this.testId = testId;
    }

//    public UserResultTO(String testName) {
//        this.testName = testName;
//    }
}
