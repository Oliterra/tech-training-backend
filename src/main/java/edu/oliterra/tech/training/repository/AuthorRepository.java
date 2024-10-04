package edu.oliterra.tech.training.repository;

import edu.oliterra.tech.training.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

}
