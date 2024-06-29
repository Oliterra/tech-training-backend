package edu.oliterra.tech.training.services;

import edu.oliterra.tech.training.dto.QuestionInfoForTemplateDTO;
import edu.oliterra.tech.training.dto.QuestionMainInfoDTO;
import edu.oliterra.tech.training.dto.pagination.QuestionsWithPaginationDTO;
import edu.oliterra.tech.training.dto.pagination.response.PaginationDTO;
import edu.oliterra.tech.training.models.QuestionEntity;
import edu.oliterra.tech.training.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionsWithPaginationDTO getAllWithPagination(int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        Page<QuestionEntity> questionPage = questionRepository.findAll(pageable);
        List<QuestionMainInfoDTO> questions = questionPage.getContent().stream()
                .map(QuestionMainInfoDTO::fromEntity)
                .toList();
        PaginationDTO paginationResponse = new PaginationDTO(
                questionPage.getNumber(),
                pageSize,
                questionPage.getTotalPages(),
                questionPage.isFirst(),
                questionPage.isLast()
        );
        return new QuestionsWithPaginationDTO(questions, paginationResponse);
    }

    @Transactional
    public List<QuestionInfoForTemplateDTO> findAll() {
        return questionRepository.findAll().stream()
                .map(QuestionInfoForTemplateDTO::fromEntity)
                .toList();
    }
}
