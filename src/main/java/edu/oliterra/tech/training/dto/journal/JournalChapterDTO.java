package edu.oliterra.tech.training.dto.journal;

import lombok.Data;

import java.util.UUID;

@Data
public class JournalChapterDTO {

    private UUID id;
    private String name;
    private String color;
    private String text;

}
