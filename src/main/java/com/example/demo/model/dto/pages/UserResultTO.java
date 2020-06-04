package com.example.demo.model.dto.pages;

import lombok.Data;

@Data
public class UserResultTO {

    String testName;

    int percent;

    public UserResultTO(String testName, int percent) {
        this.testName = testName;
        this.percent = percent;
    }

    public UserResultTO(String testName) {
        this.testName = testName;
    }
}
