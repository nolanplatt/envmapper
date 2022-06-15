package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class Surveys {
    @GetMapping("/envFootPrintSurvey")

    public String getEnvFootPrintSurvey() {
        return "envFootPrintSurvey"; 
    }


    @GetMapping("/envHabitsSurvey")

    public String getEnvHabitsSurvey() {
        return "envHabitsSurvey"; 
    }
}

