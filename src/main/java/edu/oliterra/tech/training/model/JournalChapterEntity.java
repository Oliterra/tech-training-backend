package edu.oliterra.tech.training.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "journal_chapters")
public class JournalChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "journal_id")
    private JournalEntity journal;
    private String name;
    private String color;
    @CreatedDate
    @Column(name = "created_at")
    private String createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private String updatedAt;

}
