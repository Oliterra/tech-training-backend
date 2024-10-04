package edu.oliterra.tech.training.services;

import edu.oliterra.tech.training.dto.QuestionCreationInfoDTO;
import edu.oliterra.tech.training.dto.QuestionFullInfoDTO;
import edu.oliterra.tech.training.dto.QuestionInfoForTemplateDTO;
import edu.oliterra.tech.training.dto.QuestionMainInfoDTO;
import edu.oliterra.tech.training.dto.pagination.QuestionsWithPaginationDTO;
import edu.oliterra.tech.training.dto.pagination.response.PaginationDTO;
import edu.oliterra.tech.training.exception.BadRequestException;
import edu.oliterra.tech.training.mapper.PaginationMapper;
import edu.oliterra.tech.training.mapper.QuestionMapper;
import edu.oliterra.tech.training.model.AuthorEntity;
import edu.oliterra.tech.training.model.CategoryEntity;
import edu.oliterra.tech.training.model.QuestionEntity;
import edu.oliterra.tech.training.model.TagEntity;
import edu.oliterra.tech.training.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final TagService tagService;

    private final PaginationMapper paginationMapper;
    private final QuestionMapper questionMapper;

    @Transactional
    public QuestionsWithPaginationDTO getAllWithPagination(int offset, int pageSize) {
        Page<QuestionEntity> questionsPage = questionRepository.findByOrderByCreatedAtDesc(PageRequest.of(offset, pageSize));
        List<QuestionMainInfoDTO> questions = questionMapper.buildQuestionMainInfoDTOs(questionsPage.getContent());
        PaginationDTO pagination = paginationMapper.buildQuestionsPaginationDTO(questionsPage);
        return new QuestionsWithPaginationDTO(questions, pagination);
    }

    @Transactional
    public List<QuestionInfoForTemplateDTO> getAllForTemplate() {
        List<QuestionEntity> questionEntities = questionRepository.findAll();
        return questionMapper.buildQuestionInfosForTemplate(questionEntities);
    }

    @Transactional
    public QuestionFullInfoDTO getQuestion(UUID id) {
        QuestionEntity question = getById(id);
        return questionMapper.buldQuestionFullInfoDTO(question);
    }

    public void createQuestion(QuestionCreationInfoDTO creationInfo) {
        // todo
        AuthorEntity author = authorService.getAll().getFirst();
        // AuthorEntity author = authorService.getById(creationInfo.getAuthorId());
        CategoryEntity category = categoryService.getById(creationInfo.getCategoryId());
        Set<TagEntity> tags = tagService.getTagEntities(creationInfo.getTags());
        QuestionEntity question = questionMapper.buildQuestionEntity(creationInfo, author, category, tags);
        questionRepository.save(question);
    }

    private QuestionEntity getById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Не найден вопрос с id = %s".formatted(id)));
    }

}
