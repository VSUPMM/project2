package com.example.demo.repository;

import com.example.demo.model.AnswerVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerVariant, Long> {

}