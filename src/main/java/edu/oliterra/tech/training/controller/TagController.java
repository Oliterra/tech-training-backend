package edu.oliterra.tech.training.controller;

import edu.oliterra.tech.training.dto.TagDTO;
import edu.oliterra.tech.training.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tags")
@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public List<TagDTO> getAll() {
        return tagService.getAll();
    }

}
