package com.example.demo.controller;

import com.example.demo.model.task.Task;

import com.example.demo.service.TaskService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TaskController {


    private TaskService taskService;

    @Autowired
    public void setService(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    ResponseEntity<Task> newTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> one(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> all() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> replaceTask(@RequestBody Task newTask, @PathVariable Long id) throws URISyntaxException {
        return ResponseEntity.ok(taskService.updateTask(newTask,id));
    }

    @DeleteMapping("/task/{id}")
    ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model)  {

        model.addAttribute("tasks", taskService.getAll());
        return "index";
    }
}
