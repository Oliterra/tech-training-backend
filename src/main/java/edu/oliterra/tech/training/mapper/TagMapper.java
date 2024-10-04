package edu.oliterra.tech.training.mapper;

import edu.oliterra.tech.training.dto.TagDTO;
import edu.oliterra.tech.training.model.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TagMapper {

    public abstract TagDTO buildTagDTO(TagEntity tag);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questions", ignore = true)
    public abstract TagEntity buildTagEntity(TagDTO tagDTO);

}
