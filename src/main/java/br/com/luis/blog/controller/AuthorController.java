package br.com.luis.blog.controller;


import br.com.luis.blog.domain.author.AuthorDTO;
import br.com.luis.blog.domain.author.AuthorResponseDTO;
import br.com.luis.blog.models.Author;
import br.com.luis.blog.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<AuthorResponseDTO> save(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(service.save(authorDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Author> updateById(@RequestBody AuthorDTO authorDTO, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.update(authorDTO, id),HttpStatus.OK);
    }
}
