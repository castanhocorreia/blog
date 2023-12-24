package br.com.luis.blog.domain.post;

public record PostDTO(String title, String content, Long tagId, Long authorId) {
}
