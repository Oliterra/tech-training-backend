package edu.oliterra.tech.training.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class QuestionFullInfoDTO {

    private UUID id;
    private String title;
    private String description;
    private Integer difficulty;
    private String category;
    private String journalName;
    private AuthorDTO author;
    private Set<AnswerDTO> answers;

}
