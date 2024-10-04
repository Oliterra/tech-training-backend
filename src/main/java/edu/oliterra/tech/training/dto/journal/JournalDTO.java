package edu.oliterra.tech.training.dto.journal;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class JournalDTO {

    private UUID id;
    private String name;
    private String questionName;
    private List<JournalChapterPreviewDTO> chapters = new ArrayList<>();

}
