package br.com.luis.blog.models;


import br.com.luis.blog.domain.tag.TagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private Post posts;

    public Tag(TagDTO tagDTO) {
        this.name = tagDTO.name();
    }
}
