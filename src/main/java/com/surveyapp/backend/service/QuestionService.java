package com.surveyapp.backend.service;

import com.surveyapp.backend.persistence.repositories.*;
import com.surveyapp.backend.persistence.domain.backend.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;


    public Question saveQuestion(String qType, String description, Survey obj, List<QuestionOption> optionList, String[] options) {



        Question question = questionRepository.save(new Question(qType, description,obj, optionList));

        obj.getQuestions().add(question);

        for(String optionValue: options){  //save options in option table

            QuestionOption option = optionRepository.save(new QuestionOption(optionValue, question));
            optionList.add(option);
        }
        return question;
    }
}