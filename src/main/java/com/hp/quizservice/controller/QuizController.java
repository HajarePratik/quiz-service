package com.hp.quizservice.controller;

import com.hp.quizservice.entity.QuestionWrapper;
import com.hp.quizservice.entity.QuizDTO;
import com.hp.quizservice.entity.Response;
import com.hp.quizservice.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQuestion(),
                quizDTO.getQuizTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id,
                                              @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }

}
