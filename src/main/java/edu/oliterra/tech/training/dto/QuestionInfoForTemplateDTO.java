package edu.oliterra.tech.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfoForTemplateDTO {

    private UUID id;
    private String title;
    private String description;
    private Integer difficulty;
    private String category;
    private String author;
    private String tags;
    private String comments;

}
