package edu.oliterra.tech.training.repository;

import edu.oliterra.tech.training.model.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

    Page<QuestionEntity> findByOrderByCreatedAtDesc(Pageable pageable);

}
