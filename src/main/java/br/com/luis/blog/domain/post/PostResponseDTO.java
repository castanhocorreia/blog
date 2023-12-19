package br.com.luis.blog.domain.post;

import java.time.LocalDateTime;

public record PostResponseDTO(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
