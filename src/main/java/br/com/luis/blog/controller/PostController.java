package br.com.luis.blog.controller;


import br.com.luis.blog.domain.post.PostDTO;
import br.com.luis.blog.domain.post.PostResponseDTO;
import br.com.luis.blog.models.Post;
import br.com.luis.blog.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PostResponseDTO> save(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(service.save(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Post> update(@RequestBody PostDTO postDTO, @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.update(postDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
