package br.com.luis.blog.domain.comment;

public record CommentDTO(String content, Long authorId, Long postId) {
}
