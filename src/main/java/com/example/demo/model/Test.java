package com.example.demo.model;

import com.example.demo.model.result.Result;
import com.example.demo.model.task.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "test", schema = "public")
public class Test {

  @Id @GeneratedValue
  @Column(name = "testId")
  private  Long testId;

  @Column(name = "name")
  private String name;

  @JsonManagedReference(value="Test-movement")
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(mappedBy="test", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  private List<Task> tasks;

  @JsonManagedReference(value="TestResult-movement")
  @Fetch(value = FetchMode.SUBSELECT)
  @OneToMany(mappedBy="test", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  private List<Result> results;

  public Test() {}

  public Test(String name) {
    this.name = name;
  }

}

