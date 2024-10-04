package edu.oliterra.tech.training.controller;

import edu.oliterra.tech.training.dto.CategoryDTO;
import edu.oliterra.tech.training.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Set<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

}
