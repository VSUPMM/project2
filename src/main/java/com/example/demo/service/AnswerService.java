package com.example.demo.service;


import com.example.demo.exception.AnswerNotFoundException;
import com.example.demo.model.AnswerVariant;
import com.example.demo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    public void setRepository(AnswerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void deleteAnswer(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<AnswerVariant> getAll() {
        List<AnswerVariant> answerEntities = repository.findAll();
        return answerEntities;
    }

    @Transactional
    public AnswerVariant getById(Long id) {
        Optional<AnswerVariant> answer = repository.findById(id);

        return  answer.orElseThrow(() -> new AnswerNotFoundException(id));
    }

    @Transactional
    public AnswerVariant createAnswer(AnswerVariant newAnswer) {
        newAnswer = repository.save(newAnswer);
        return newAnswer;
    }

    @Transactional
    public AnswerVariant updateAnswer(AnswerVariant newAnswer, Long id) {

        AnswerVariant updatedAnswer = repository.findById(id)
                .map(answer -> {
                    answer.setText(newAnswer.getText());
                    answer.setCorrect(newAnswer.getCorrect());
                    return repository.save(answer);
                })
                .orElseGet(() -> {
                    newAnswer.setAnswerId(id);
                    return repository.save(newAnswer);
                });

        return updatedAnswer;
    }

}