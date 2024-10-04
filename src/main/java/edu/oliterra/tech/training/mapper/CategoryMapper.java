package edu.oliterra.tech.training.mapper;

import edu.oliterra.tech.training.dto.CategoryDTO;
import edu.oliterra.tech.training.model.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CategoryMapper {

    public abstract CategoryDTO buildCategoryDTO(CategoryEntity category);

}
