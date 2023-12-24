package br.com.luis.blog.domain.post;

import br.com.luis.blog.models.Author;
import br.com.luis.blog.models.Comment;
import br.com.luis.blog.models.Tag;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Author author,
        List<Tag> tag,
        List<Comment> comment) {
}
