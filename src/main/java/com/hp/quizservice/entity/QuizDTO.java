package com.hp.quizservice.entity;

import lombok.Data;

@Data
public class QuizDTO {

    private String category;
    private int numQuestion;
    private String quizTitle;
}
