package br.com.luis.blog.domain.tag;

import java.time.LocalDateTime;

public record TagResponseDTO(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
