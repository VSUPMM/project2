package com.example.demo.controller;//package com.example.demo.controller;

import com.example.demo.model.dto.pages.ResultTo;
import com.example.demo.model.dto.pages.TestResultTO;
import com.example.demo.model.dto.pages.UserResultTO;
import com.example.demo.model.result.Result;
import com.example.demo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ResultController {

    private ResultService service;

    @Autowired
    public void setService(ResultService service) {
        this.service = service;
    }

    @PostMapping("/result/user/{userId}/task/{taskId}")
    public ResponseEntity<?> newtaskResult(@PathVariable Long userId, @PathVariable Long taskId,
                                          @RequestBody Result result) {
        return ResponseEntity.ok(service.createResult(userId,taskId,result));
    }

    @GetMapping("/result/user/{userId}/task/{taskId}")
    public ResponseEntity<Result> one(@PathVariable Long userId, @PathVariable Long taskId) {
        return ResponseEntity.ok(service.getById(userId,taskId));
    }

    @PostMapping("/result/user/{userId}")
    public ResponseEntity<Result> addResults(@PathVariable Long userId, @RequestBody ResultTo filter) {
        return ResponseEntity.ok(service.createResult(userId,filter));
    }

    @GetMapping("/results")
    public ResponseEntity<List<Result>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/statistics/user/{userId}")
    public ResponseEntity<List<UserResultTO>> getUserStatistics(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserStatistics(userId));
    }

    @GetMapping("/statistics/test/{testId}")
    public ResponseEntity<List<TestResultTO>> getTestStatistics(@PathVariable Long testId) {
        return ResponseEntity.ok(service.getTestStatistics(testId));
    }

//    @PutMapping("/results/user/{userId}/task/{taskId}")
//    ResponseEntity<?> replacetask(@RequestBody Result newResult, @PathVariable Long userId,@PathVariable Long taskId) {
//        return ResponseEntity.ok(service.updateResult(newResult,userId,taskId));
//    } //todo

    @DeleteMapping("/result/user/{userId}/task/{taskId}")
    public ResponseEntity<Object> deletetask(@PathVariable Long userId,@PathVariable Long taskId) {
        service.deleteResult(userId,taskId);
        return ResponseEntity.noContent().build();
    }
}
