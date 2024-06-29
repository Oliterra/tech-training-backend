package edu.oliterra.tech.training.dto;

import edu.oliterra.tech.training.models.QuestionEntity;
import edu.oliterra.tech.training.models.TagEntity;
import edu.oliterra.tech.training.models.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfoForTemplateDTO {

    private UUID id;
    private String title;
    private String description;
    private String difficulty;
    private String category;
    private String author;
    private String tags;
    private String comments;

    public static QuestionInfoForTemplateDTO fromEntity(QuestionEntity questionEntity) {
        return new QuestionInfoForTemplateDTO(
                questionEntity.getId(),
                questionEntity.getTitle(),
                questionEntity.getDescription(),
                questionEntity.getDifficulty(),
                questionEntity.getCategory().getName(),
                questionEntity.getAuthor().getLogin(),
                questionEntity.getTags().stream()
                        .map(TagEntity::getName)
                        .collect(Collectors.joining(", ")),
                questionEntity.getComments().stream()
                        .map(CommentEntity::getText)
                        .collect(Collectors.joining("\n")));
    }
}
