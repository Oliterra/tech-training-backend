package edu.oliterra.tech.training.controller;

import edu.oliterra.tech.training.dto.pagination.QuestionsWithPaginationDTO;
import edu.oliterra.tech.training.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/questions")
@RestController
@RequiredArgsConstructor
public class QuestionRestController {

    private final QuestionService questionService;

    @GetMapping("/paginate")
    public QuestionsWithPaginationDTO getAllWithPagination(@RequestParam("offset") int offset,
                                                           @RequestParam("pageSize") int pageSize) {
        return questionService.getAllWithPagination(offset, pageSize);
    }
}
