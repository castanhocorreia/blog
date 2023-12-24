package br.com.luis.blog.domain.comment;

import br.com.luis.blog.models.Author;
import br.com.luis.blog.models.Post;

import java.time.LocalDateTime;

public record CommentResponseDTO(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Author author, Post post) {
}
