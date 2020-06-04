package com.example.demo.controller;

import com.example.demo.model.Test;
import com.example.demo.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class TestController {


    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/test")
    ResponseEntity<Test> newTest(@RequestBody Test newTest) {
        return ResponseEntity.ok(testService.createTest(newTest));
    }


    @GetMapping("/test/{id}")
    public ResponseEntity<Test> one(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getById(id));
    }


    @GetMapping("/tests")
    public ResponseEntity<List<Test>> all() {
       return ResponseEntity.ok(testService.getAll());
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("/test/{id}")
    public ResponseEntity<Test> replaceTest(@RequestBody Test newTest, @PathVariable Long id) throws URISyntaxException {
       return ResponseEntity.ok(testService.updateTest(newTest,id));
    }

    @DeleteMapping("/test/{id}")
    ResponseEntity<Object> deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}
