package edu.oliterra.tech.training.services;

import edu.oliterra.tech.training.dto.CategoryDTO;
import edu.oliterra.tech.training.exception.BadRequestException;
import edu.oliterra.tech.training.mapper.CategoryMapper;
import edu.oliterra.tech.training.model.CategoryEntity;
import edu.oliterra.tech.training.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public Set<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::buildCategoryDTO)
                .collect(Collectors.toSet());
    }

    public CategoryEntity getById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new BadRequestException("Не найдена категория с id = %s".formatted(id)));
    }

}
