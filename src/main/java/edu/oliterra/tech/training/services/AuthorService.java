package edu.oliterra.tech.training.services;

import edu.oliterra.tech.training.exception.BadRequestException;
import edu.oliterra.tech.training.model.AuthorEntity;
import edu.oliterra.tech.training.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorEntity> getAll() {
        return authorRepository.findAll();
    }

    public AuthorEntity getById(UUID id) {
        return authorRepository.findById(id).orElseThrow(() -> new BadRequestException("Не найден автор с id = %s".formatted(id)));
    }

}
