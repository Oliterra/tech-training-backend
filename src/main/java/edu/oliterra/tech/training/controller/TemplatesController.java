package edu.oliterra.tech.training.controller;

import edu.oliterra.tech.training.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TemplatesController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String questionsListPage(Model model) {
        long before = System.currentTimeMillis();
        var questions = questionService.findAll();
        long after = System.currentTimeMillis();

        model.addAttribute("duration", (after - before) / 1000);
        model.addAttribute("questions", questions);
        return "questionList";
    }
}
