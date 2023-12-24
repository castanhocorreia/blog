package br.com.luis.blog.controller;

import br.com.luis.blog.domain.comment.CommentDTO;
import br.com.luis.blog.domain.comment.CommentResponseDTO;
import br.com.luis.blog.models.Comment;
import br.com.luis.blog.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService service;

    @PostMapping
    @Transactional
    public ResponseEntity<CommentResponseDTO> save(@RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(service.save(commentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

   /* @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Comment> findById(@RequestBody CommentDTO commentDTO, @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.update(commentDTO, id), HttpStatus.OK);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
