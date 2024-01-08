package com.quiz.quizservice.controller;

import com.quiz.quizservice.entity.Quiz;
import com.quiz.quizservice.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    //create

    private final QuizService quizService;


    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/createQuiz")
    public Quiz create(@RequestBody Quiz quiz){
        return quizService.add(quiz);
    }


    @GetMapping("/allQuiz")
    public List<Quiz> get(){
        return quizService.get();
    }

    @GetMapping("/{id}")
    public Quiz getOne(@PathVariable Long id){
        return quizService.get(id);
    }



}
