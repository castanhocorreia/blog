package br.com.luis.blog.service;


import br.com.luis.blog.domain.comment.CommentDTO;
import br.com.luis.blog.domain.comment.CommentResponseDTO;
import br.com.luis.blog.models.Author;
import br.com.luis.blog.models.Comment;
import br.com.luis.blog.models.Post;
import br.com.luis.blog.repositories.AuthorRepository;
import br.com.luis.blog.repositories.CommentRepository;
import br.com.luis.blog.repositories.PostRepository;
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

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public Comment findById(Long id) {
        Optional<Comment> commentOptional = repository.findById(id);

        if (commentOptional.isPresent()) {
            return commentOptional.get();
        }
        throw new NoSuchElementException("Nenhum comentário encontrado");
    }

    public CommentResponseDTO save(CommentDTO commentDTO) {

        Author author = authorRepository.findById(commentDTO.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Author não encontrado"));

        Post post = postRepository.findById(commentDTO.postId())
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        Comment newComment = new Comment(commentDTO);
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setAuthor(author);
        newComment.setPost(post);
        Comment saveComment = repository.save(newComment);

        return new CommentResponseDTO(saveComment.getId(), saveComment.getContent(),
                saveComment.getCreatedAt(), saveComment.getUpdatedAt(), saveComment.getAuthor(), saveComment.getPost());
    }

   /* public Comment update(CommentDTO commentDTO, Long id) {
        Optional<Comment> commentOptional = repository.findById(id);

        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado");
        }

        Comment comment = commentOptional.get();
        comment.setContent(commentDTO.content());
        comment.setUpdatedAt(LocalDateTime.now());

        Comment updatedComment = repository.save(comment);

        return new Comment(updatedComment.getId(), updatedComment.getContent(),
                updatedComment.getCreatedAt(), updatedComment.getUpdatedAt());
    }*/

    public void delete(Long id) {
        Optional<Comment> comment = repository.findById(id);

        if (comment.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Id Inválido");

    }
}
