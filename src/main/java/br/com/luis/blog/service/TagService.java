package br.com.luis.blog.service;


import br.com.luis.blog.domain.tag.TagDTO;
import br.com.luis.blog.domain.tag.TagResponseDTO;
import br.com.luis.blog.models.Tag;
import br.com.luis.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TagService {


    @Autowired
    private TagRepository repository;


    public TagResponseDTO save(TagDTO tagDTO) {
        Tag newTag = new Tag(tagDTO);
        newTag.setCreatedAt(LocalDateTime.now());
        Tag saveTag = repository.save(newTag);


        return new TagResponseDTO(saveTag.getId(), saveTag.getName(), saveTag.getCreatedAt(), saveTag.getUpdatedAt());
    }

    public List<Tag> findAll() {
        return repository.findAll();
    }

    public Tag findById(Long id) {
        Optional<Tag> tagOptional = repository.findById(id);

        if (tagOptional.isPresent()) {
            return tagOptional.get();
        }
        throw new NoSuchElementException("Nenhuma Tag encontrada");
    }

  /*  public Tag update(TagDTO tagDTO, Long id) {
        Optional<Tag> tagOptional = repository.findById(id);

        if (tagOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado");
        }

        Tag tag = tagOptional.get();
        tag.setName(tagDTO.name());
        tag.setUpdatedAt(LocalDateTime.now());

        Tag updatedTag = repository.save(tag);

        return new Tag(updatedTag.getId(), updatedTag.getName(), updatedTag.getCreatedAt(), updatedTag.getUpdatedAt());
    }*/

    public void delete(Long id) {
        Optional<Tag> tag = repository.findById(id);

        if (tag.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Id não encontrado");
    }
}
