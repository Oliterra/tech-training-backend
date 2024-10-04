package edu.oliterra.tech.training.mapper;

import edu.oliterra.tech.training.dto.QuestionCreationInfoDTO;
import edu.oliterra.tech.training.dto.QuestionFullInfoDTO;
import edu.oliterra.tech.training.dto.QuestionInfoForTemplateDTO;
import edu.oliterra.tech.training.dto.QuestionMainInfoDTO;
import edu.oliterra.tech.training.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class QuestionMapper {

    protected static final String STRING_DELIMITER = ", ";

    public abstract List<QuestionMainInfoDTO> buildQuestionMainInfoDTOs(List<QuestionEntity> questionEntities);

    public abstract List<QuestionInfoForTemplateDTO> buildQuestionInfosForTemplate(List<QuestionEntity> questionEntities);

    @Mapping(target = "category", source = "questionEntity.category.name")
    @Mapping(target = "tags", source = "questionEntity.tags", qualifiedByName = "toTags")
    public abstract QuestionMainInfoDTO buildQuestionMainInfoDTO(QuestionEntity questionEntity);

    @Mapping(target = "category", source = "questionEntity.category.name")
    @Mapping(target = "author", source = "questionEntity.author.login")
    @Mapping(target = "tags", source = "questionEntity.tags", qualifiedByName = "toTagsSeparated")
    @Mapping(target = "comments", source = "questionEntity.comments", qualifiedByName = "toComments")
    public abstract QuestionInfoForTemplateDTO buildQuestionInfoForTemplateDTO(QuestionEntity questionEntity);

    @Mapping(target = "category", source = "questionEntity.category.name")
    @Mapping(target = "journalName", constant = "name")
    //@Mapping(target = "journalName", source = "questionEntity.journal.name")
    public abstract QuestionFullInfoDTO buldQuestionFullInfoDTO(QuestionEntity questionEntity);

    @Mapping(target = "description", source = "creationInfo.description")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "journal", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "answers", ignore = true)
    public abstract QuestionEntity buildQuestionEntity(QuestionCreationInfoDTO creationInfo,
                                                       AuthorEntity author, CategoryEntity category, Set<TagEntity> tags);

    @Named("toTags")
    protected List<String> toTags(Set<TagEntity> tags) {
        return tags.stream()
                .map(TagEntity::getName)
                .toList();
    }

    @Named("toTagsSeparated")
    protected String toTagsSeparated(Set<TagEntity> tags) {
        return tags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.joining(STRING_DELIMITER));
    }

    @Named("toComments")
    protected String toComments(Set<CommentEntity> comments) {
        return comments.stream()
                .map(CommentEntity::getText)
                .collect(Collectors.joining(STRING_DELIMITER));
    }

}
