//package com.example.demo.model.Result;
//
//import com.example.demo.model.Test;
//import com.example.demo.model.User;
//import com.example.demo.model.embeddedId.ResultId;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "test_result")
//public class TestResult {
//
//    @Id
//    private ResultId id;
//
//    @JsonManagedReference(value="Test-Result-movement")
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @MapsId("testId")
//    @JoinColumn(name = "testId")
//    private Test test;
//
//    @JsonManagedReference(value="Result-movement")
//    @Fetch(value = FetchMode.SUBSELECT)
//    @OneToMany(mappedBy="testUser", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<Result> results;
//
//}
//
