package br.com.luis.blog.service;


import br.com.luis.blog.domain.comment.CommentDTO;
import br.com.luis.blog.domain.comment.CommentResponseDTO;
import br.com.luis.blog.models.Comment;
import br.com.luis.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;


    public List<Comment> findAll() {
        List<Comment> comments = repository.findAll();

        if (comments.isEmpty()) {
            throw new NoSuchElementException("Nenhum comentário encontrado");
        }
        return comments;
    }

    public Comment findById(Long id) {
        Optional<Comment> commentOptional = repository.findById(id);

        if (commentOptional.isPresent()) {
            return commentOptional.get();
        }
        throw new NoSuchElementException("Nenhum comentário encontrado");
    }

    public CommentResponseDTO save(CommentDTO commentDTO) {
        Comment newComment = new Comment(commentDTO);
        newComment.setCreatedAt(LocalDateTime.now());
        Comment saveComment = repository.save(newComment);

        return new CommentResponseDTO(saveComment.getId(), saveComment.getContent(),
                saveComment.getCreatedAt(), saveComment.getUpdatedAt());
    }

    public Comment update(CommentDTO commentDTO, Long id) {
        Optional<Comment> commentOptional = repository.findById(id);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setContent(commentDTO.content());
            comment.setUpdatedAt(LocalDateTime.now());

            Comment updatedComment = repository.save(comment);

            return new Comment(updatedComment.getId(), updatedComment.getContent(),
                    updatedComment.getCreatedAt(), updatedComment.getUpdatedAt());
        }
        throw new IllegalArgumentException("Id não encontrado");
    }

    public void delete(Long id) {
        Optional<Comment> comment = repository.findById(id);

        if (comment.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Id Inválido");

    }
}
