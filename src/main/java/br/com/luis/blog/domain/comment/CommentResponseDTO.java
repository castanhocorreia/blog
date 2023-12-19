package br.com.luis.blog.domain.comment;

import java.time.LocalDateTime;

public record CommentResponseDTO(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
