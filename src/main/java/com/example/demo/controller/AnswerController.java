package com.example.demo.controller;

import com.example.demo.model.AnswerVariant;
import com.example.demo.service.AnswerService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @JsonBackReference(value="Test-movement")
    @PostMapping("/answer")
    ResponseEntity<AnswerVariant> newAnswer(@RequestBody AnswerVariant answer) {
        return ResponseEntity.ok(answerService.createAnswer(answer));
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<AnswerVariant> one(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.getById(id));
    }

    @GetMapping("/answers")
    public ResponseEntity<List<AnswerVariant>> all() {
        return ResponseEntity.ok(answerService.getAll());
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @JsonBackReference(value="Test-movement")
    @PutMapping("/answer/{id}")
    public ResponseEntity<AnswerVariant> replaceAnswer(@RequestBody AnswerVariant newAnswer,
                                                       @PathVariable Long id) throws URISyntaxException {
        return ResponseEntity.ok(answerService.updateAnswer(newAnswer,id));
    }

    @DeleteMapping("/answer/{id}")
    ResponseEntity<Object> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

}
