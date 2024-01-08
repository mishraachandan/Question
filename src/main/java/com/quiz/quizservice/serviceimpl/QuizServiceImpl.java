package com.quiz.quizservice.serviceimpl;

import com.quiz.quizservice.entity.Quiz;
import com.quiz.quizservice.repository.QuizRepository;
import com.quiz.quizservice.service.QuestionClient;
import com.quiz.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private final QuestionClient questionClient;


    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }


    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> list = quizRepository.findAll();
        List<Quiz> newList = new ArrayList<>();
        for(Quiz quiz : list){
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            newList.add(quiz);
        }
        return newList;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Sorry, not data found."));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
}
