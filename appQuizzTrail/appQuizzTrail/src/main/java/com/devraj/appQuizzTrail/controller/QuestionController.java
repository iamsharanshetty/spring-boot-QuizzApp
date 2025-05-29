package com.devraj.appQuizzTrail.controller;

import com.devraj.appQuizzTrail.model.Question;
import com.devraj.appQuizzTrail.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getallqs(){
        return questionService.getallqs();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getallqbycat(@PathVariable String category){
        return questionService.getallqbycat(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addqs(@RequestBody Question question){
        return questionService.addqs(question);
    }
}
