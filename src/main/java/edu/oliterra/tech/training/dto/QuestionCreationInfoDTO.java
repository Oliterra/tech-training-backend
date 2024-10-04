package edu.oliterra.tech.training.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class QuestionCreationInfoDTO {

    private String title;
    private String description;
    private Integer difficulty;
    private UUID authorId;
    private UUID categoryId;
    private Set<TagDTO> tags;

}
