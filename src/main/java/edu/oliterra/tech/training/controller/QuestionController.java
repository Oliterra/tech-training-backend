package edu.oliterra.tech.training.controller;

import edu.oliterra.tech.training.dto.QuestionCreationInfoDTO;
import edu.oliterra.tech.training.dto.QuestionFullInfoDTO;
import edu.oliterra.tech.training.dto.pagination.QuestionsWithPaginationDTO;
import edu.oliterra.tech.training.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/questions")
@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/paginate")
    public QuestionsWithPaginationDTO getAllWithPagination(@RequestParam("offset") int offset,
                                                           @RequestParam("pageSize") int pageSize) {
        return questionService.getAllWithPagination(offset, pageSize);
    }

    @GetMapping("/{id}")
    public QuestionFullInfoDTO getQuestion(@PathVariable UUID id) {
        return questionService.getQuestion(id);
    }

    @PostMapping("/create")
    public void createQuestion(@RequestBody QuestionCreationInfoDTO creationInfo) {
        questionService.createQuestion(creationInfo);
    }

}
