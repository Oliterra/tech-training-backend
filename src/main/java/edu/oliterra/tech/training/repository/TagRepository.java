package edu.oliterra.tech.training.repository;

import edu.oliterra.tech.training.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {

    List<TagEntity> findByOrderByName();

}
