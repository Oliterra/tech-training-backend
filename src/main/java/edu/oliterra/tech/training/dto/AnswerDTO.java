package edu.oliterra.tech.training.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AnswerDTO {

    private UUID id;
    private AuthorDTO author;
    private String text;
    private LocalDateTime createdAt;

}
