package edu.oliterra.tech.training.controller;

import edu.oliterra.tech.training.dto.journal.JournalChapterDTO;
import edu.oliterra.tech.training.dto.journal.JournalChapterPreviewDTO;
import edu.oliterra.tech.training.dto.journal.JournalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RequestMapping("/journal")
@RestController
@RequiredArgsConstructor
public class JournalController {

    @GetMapping("/{name}")
    public JournalDTO getJournal(@PathVariable String name) {
        JournalDTO journalDTO = new JournalDTO();
        journalDTO.setQuestionName("название вопроса");
        journalDTO.setName("journal name");
        journalDTO.setChapters(List.of(
                getJournalChapterPreview("chapter 1"),
                getJournalChapterPreview("chapter 2"),
                getJournalChapterPreview("chapter 3"),
                getJournalChapterPreview("chapter 4"),
                getJournalChapterPreview("chapter 5"),
                getJournalChapterPreview("chapter 6"),
                getJournalChapterPreview("chapter 7"),
                getJournalChapterPreview("chapter 8"),
                getJournalChapterPreview("chapter 9"),
                getJournalChapterPreview("chapter 10"),
                getJournalChapterPreview("chapter 11"),
                getJournalChapterPreview("chapter 12"),
                getJournalChapterPreview("chapter 13"),
                getJournalChapterPreview("chapter 14"),
                getJournalChapterPreview("chapter 15")
        ));
        return journalDTO;
    }

    @GetMapping("/chapter/{name}")
    public JournalChapterDTO getJournalChapter(@PathVariable String name) {
        JournalChapterDTO journalDTO = new JournalChapterDTO();
        journalDTO.setName(name);
        String chapterContent = name + " content ";
        //journalDTO.setText("<div class=\"bold-text\">" + chapterContent.repeat(10) + "</div>");
        //journalDTO.setText(chapterContent.repeat(10));
        journalDTO.setText("123456789 12 15 18 21 24 27 30 33 36 36 42 45");
        journalDTO.setColor("#12AD2BFF");
        return journalDTO;
    }

    private JournalChapterPreviewDTO getJournalChapterPreview(String name) {
        List<String> colors = List.of(
                "#D32727FF",
                "#2791D3FF",
                "#00FFE2FF",
                "#12AD2BFF",
                "#ADAA12FF",
                "#1227ADFF",
                "#AD12A0FF",
                "#DC5F13FF",
                "#E05871FF",
                "#00E1FFFF");
        JournalChapterPreviewDTO journalChapterPreviewDTO = new JournalChapterPreviewDTO();
        journalChapterPreviewDTO.setName(name);
        Random random = new Random();
        int randomIndex = random.nextInt(colors.size());
        journalChapterPreviewDTO.setColor(colors.get(randomIndex));
        return journalChapterPreviewDTO;
    }

}
