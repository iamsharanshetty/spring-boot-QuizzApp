package com.devraj.appQuizzTrail.controller;

import com.devraj.appQuizzTrail.model.Response;
import com.devraj.appQuizzTrail.model.Question;
import com.devraj.appQuizzTrail.model.QuestionWrapper;
import com.devraj.appQuizzTrail.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

@PostMapping("create")
public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numq, @RequestParam String title){
    return quizService.createQuiz(category,numq,title);
}

@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
    return quizService.getQuiz(id);
}

@PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
    return quizService.calculateQuiz(id,responses);
}
}
