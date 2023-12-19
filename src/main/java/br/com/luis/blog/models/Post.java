package br.com.luis.blog.models;

import br.com.luis.blog.domain.post.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Post(PostDTO postDTO) {
        this.title = postDTO.title();
        this.content = postDTO.content();
    }

}
