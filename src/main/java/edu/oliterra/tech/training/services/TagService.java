package edu.oliterra.tech.training.services;

import edu.oliterra.tech.training.dto.TagDTO;
import edu.oliterra.tech.training.mapper.TagMapper;
import edu.oliterra.tech.training.model.TagEntity;
import edu.oliterra.tech.training.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    public final TagRepository tagRepository;

    public final TagMapper tagMapper;

    public List<TagDTO> getAll() {
        return tagRepository.findByOrderByName().stream()
                .map(tagMapper::buildTagDTO)
                .toList();
    }

    public Set<TagEntity> getTagEntities(Set<TagDTO> tags) {
        Set<UUID> tagIds = tags.stream()
                .map(TagDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Set<TagEntity> existingTags = new HashSet<>(tagRepository.findAllById(tagIds));
        Set<TagEntity> newTags = tags.stream()
                .filter(tag -> tag.getId() == null)
                .map(tagMapper::buildTagEntity)
                .collect(Collectors.toSet());
        existingTags.addAll(tagRepository.saveAll(newTags));
        return existingTags;
    }

}
