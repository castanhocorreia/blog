package br.com.luis.blog.service;


import br.com.luis.blog.domain.post.PostDTO;
import br.com.luis.blog.models.Post;
import br.com.luis.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    private PostRepository repository;


    public Post save(PostDTO postDTO) {
        Post newPost = new Post(postDTO);
        return repository.save(newPost);
    }

    public List<Post> findAll() {
        List<Post> posts = repository.findAll();

        if (posts.isEmpty()) {
            throw new NoSuchElementException("Nenhum post encontrado");
        }
        return posts;
    }

    public Post findById(Long id) {
        Optional<Post> postOptional = repository.findById(id);

        if (postOptional.isPresent()) {
            return postOptional.get();
        }
        throw new NoSuchElementException("Nenhum post encontrado");
    }

    public void delete(Long id) {
        Optional<Post> post = repository.findById(id);

        if (post.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Id Inválido");
    }

    public Post update(PostDTO postDTO, Long id) {
        Optional<Post> postOptional = repository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(postDTO.title());
            post.setContent(postDTO.content());
            Post updatedPost = repository.save(post);
            return new Post(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent());
        }
        throw new IllegalArgumentException("Id não encontrado");
    }
}
