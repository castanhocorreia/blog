package br.com.luis.blog.domain.author;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AuthorResponseDTO(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
