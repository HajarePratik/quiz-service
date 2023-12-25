package com.hp.quizservice.service;

import com.hp.quizservice.entity.QuestionWrapper;
import com.hp.quizservice.entity.Quiz;
import com.hp.quizservice.entity.Response;
import com.hp.quizservice.feign.QuizInterface;
import com.hp.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQuestion, String quizTitle) {

        List<Integer> questionList = quizInterface.getQuestionForQuiz(category, numQuestion).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);

        quiz.setQuestionIds(questionList);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Create a Quiz Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionListFromDB = quiz.getQuestionIds();

        ResponseEntity<List<QuestionWrapper>> response =
                quizInterface.getQuestionFromId(questionListFromDB);

        return response;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

}
