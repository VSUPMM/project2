package com.example.demo.model.dto.pages;

import lombok.Data;

@Data
public class TestResultTO {

    String name;

    String surname;

    int percent;

    public TestResultTO(String name, String surname, int percent) {
        this.name = name;
        this.surname = surname;
        this.percent = percent;
    }

    public TestResultTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
