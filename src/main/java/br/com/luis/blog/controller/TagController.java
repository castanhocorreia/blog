package br.com.luis.blog.controller;


import br.com.luis.blog.domain.tag.TagDTO;
import br.com.luis.blog.domain.tag.TagResponseDTO;
import br.com.luis.blog.models.Tag;
import br.com.luis.blog.service.TagService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService service;

    @PostMapping
    @Transactional
    public ResponseEntity<TagResponseDTO> save(@RequestBody TagDTO tagDTO) {
        return new ResponseEntity<>(service.save(tagDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    /*@PutMapping("{id}")
    @Transactional
    public ResponseEntity<Tag> update(@RequestBody TagDTO tagDTO, @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.update(tagDTO, id), HttpStatus.OK);
    }*/

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
