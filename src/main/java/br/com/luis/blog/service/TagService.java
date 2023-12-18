package br.com.luis.blog.service;


import br.com.luis.blog.domain.tag.TagDTO;
import br.com.luis.blog.models.Tag;
import br.com.luis.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TagService {


    @Autowired
    private TagRepository repository;


    public Tag save(TagDTO tagDTO) {
        Tag newTag = new Tag(tagDTO);
        return repository.save(newTag);
    }

    public List<Tag> findAll() {
        List<Tag> tags = repository.findAll();

        if (tags.isEmpty()) {
            throw new NoSuchElementException("Nenhuma Tag encontrada");
        }
        return tags;
    }

    public Tag findById(Long id) {
        Optional<Tag> tagOptional = repository.findById(id);

        if (tagOptional.isPresent()) {
            return tagOptional.get();
        }
        throw new NoSuchElementException("Nenhuma Tag encontrada");
    }

    public Tag update(TagDTO tagDTO, Long id) {
        Optional<Tag> tagOptional = repository.findById(id);

        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            tag.setName(tagDTO.name());
            Tag updatedTag = repository.save(tag);
            return new Tag(updatedTag.getId(), updatedTag.getName());
        }
        throw new IllegalArgumentException("Id não encontrado");
    }

    public void delete(Long id) {
        Optional<Tag> tag = repository.findById(id);

        if (tag.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Id não encontrado");
    }
}
