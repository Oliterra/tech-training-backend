package edu.oliterra.tech.training.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorDTO {

    private UUID id;
    private String login;

}
