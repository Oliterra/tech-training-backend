package edu.oliterra.tech.training.services;

import edu.oliterra.tech.training.dto.QuestionDTO;
import edu.oliterra.tech.training.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public List<QuestionDTO> findAll() {
        return questionRepository.findAll().stream()
                .map(QuestionDTO::fromDomainObject)
                .toList();
    }
}
