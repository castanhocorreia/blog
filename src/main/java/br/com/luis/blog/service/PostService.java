package br.com.luis.blog.service;


import br.com.luis.blog.domain.post.PostDTO;
import br.com.luis.blog.domain.post.PostResponseDTO;
import br.com.luis.blog.domain.tag.TagViewDTO;
import br.com.luis.blog.models.Author;
import br.com.luis.blog.models.Post;
import br.com.luis.blog.models.Tag;
import br.com.luis.blog.repositories.AuthorRepository;
import br.com.luis.blog.repositories.PostRepository;
import br.com.luis.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {


    @Autowired
    private PostRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TagRepository tagRepository;


    public PostResponseDTO save(PostDTO postDTO) {

        Author author = authorRepository.findById(postDTO.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Author não encontrado"));

        Tag tag = tagRepository.findById(postDTO.tagId())
                .orElseThrow(() -> new IllegalArgumentException("Tag não encontrada"));

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);

        Post newPost = new Post(postDTO);
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setAuthor(author);
        newPost.setTags(tags);
        Post savePost = repository.save(newPost);

        return new PostResponseDTO(savePost.getId(), savePost.getTitle(), savePost.getContent(),
                savePost.getCreatedAt(), savePost.getUpdatedAt(), savePost.getAuthor(), savePost.getTags(), savePost.getComments());
    }

    public List<Post> findAll() {
        return repository.findAll();
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

        if (postOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado");
        }
        Post post = postOptional.get();
        post.setTitle(postDTO.title());
        post.setContent(postDTO.content());
        post.setUpdatedAt(LocalDateTime.now());

        Post updatedPost = repository.save(post);

        return new Post(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent(),
                updatedPost.getCreatedAt(), updatedPost.getUpdatedAt(), updatedPost.getAuthor(), updatedPost.getComments()
        ,updatedPost.getTags());
    }
}
