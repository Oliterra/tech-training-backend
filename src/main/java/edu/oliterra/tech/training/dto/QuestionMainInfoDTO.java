package edu.oliterra.tech.training.dto;

import edu.oliterra.tech.training.models.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionMainInfoDTO {

    private UUID id;
    private String title;
    private String description;
    private String difficulty;
    private String category;
    private String author;

    public static QuestionMainInfoDTO fromEntity(QuestionEntity questionEntity) {
        return new QuestionMainInfoDTO(
                questionEntity.getId(),
                questionEntity.getTitle(),
                questionEntity.getDescription(),
                questionEntity.getDifficulty(),
                questionEntity.getCategory().getName(),
                questionEntity.getAuthor().getLogin());
    }
}
