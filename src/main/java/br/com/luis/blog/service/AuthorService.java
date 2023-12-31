package br.com.luis.blog.service;

import br.com.luis.blog.domain.author.AuthorDTO;
import br.com.luis.blog.domain.author.AuthorResponseDTO;
import br.com.luis.blog.models.Author;
import br.com.luis.blog.models.Tag;
import br.com.luis.blog.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public List<Author> findAll() {
        return repository.findAll();
    }

    public Author findById(Long id) {
        Optional<Author> authorOptional = repository.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        }
        throw new NoSuchElementException("Não foi encontrado nenhum autor");
    }

    public AuthorResponseDTO save(AuthorDTO authorDTO) {
        Author newAuthor = new Author(authorDTO);
        newAuthor.setCreatedAt(LocalDateTime.now());
        Author saveAuthor = repository.save(newAuthor);

        return new AuthorResponseDTO(saveAuthor.getId(), saveAuthor.getName(), saveAuthor.getCreatedAt(),
                saveAuthor.getUpdatedAt());
    }

    public Author update(AuthorDTO authorDTO, Long id) {
        Optional<Author> authorOptional = repository.findById(id);

        if (authorOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não encontrado");
        }
        Author author = authorOptional.get();
        author.setName(authorDTO.name());
        author.setUpdatedAt(LocalDateTime.now());

        Author updatedAuthor = repository.save(author);


        return new Author(updatedAuthor.getId(), updatedAuthor.getName(),
                updatedAuthor.getCreatedAt(), updatedAuthor.getUpdatedAt(), updatedAuthor.getPosts());
    }

    public void delete(Long id) {
        Optional<Author> author = repository.findById(id);
        if (author.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new IllegalArgumentException("Id Inválido");
    }
}
