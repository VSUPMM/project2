package com.example.demo.repository;

import com.example.demo.model.Test;
import com.example.demo.model.User;
import com.example.demo.model.embeddedId.ResultId;
import com.example.demo.model.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResultRepository extends JpaRepository<Result, ResultId> {

    List<Result> findByTest (Test test);

    List<Result> findByUserAndTest (User user, Test test);

}
