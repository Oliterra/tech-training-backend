package edu.oliterra.tech.training.mapper;

import edu.oliterra.tech.training.dto.pagination.response.PaginationDTO;
import edu.oliterra.tech.training.model.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PaginationMapper {

    @Mapping(target = "offset", source = "number")
    @Mapping(target = "pageSize", source = "numberOfElements")
    public abstract PaginationDTO buildQuestionsPaginationDTO(Page<QuestionEntity> questionPage);

}
