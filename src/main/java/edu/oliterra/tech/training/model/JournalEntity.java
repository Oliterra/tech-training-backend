package edu.oliterra.tech.training.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "journals")
public class JournalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
    @Column(name = "name")
    private String name;

}
