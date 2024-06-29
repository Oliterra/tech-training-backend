package edu.oliterra.tech.training.dto.pagination;

import edu.oliterra.tech.training.dto.QuestionMainInfoDTO;
import edu.oliterra.tech.training.dto.pagination.response.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsWithPaginationDTO {
    
    private List<QuestionMainInfoDTO> questions;
    private PaginationDTO pagination;
}
