package br.com.luis.blog.service;

import br.com.luis.blog.domain.author.AuthorDTO;
import br.com.luis.blog.models.Author;
import br.com.luis.blog.models.Tag;
import br.com.luis.blog.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public List<Author> findAll() {
        List<Author> authors = repository.findAll();

        if (authors.isEmpty()) {
            throw new NoSuchElementException("Nenhum autor encontrado.");
        }

        return authors;
    }

    public Author findById(Long id) {
        Optional<Author> authorOptional = repository.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        }
        throw new NoSuchElementException("Não foi encontrado nenhum autor");
    }

    public Author save(AuthorDTO authorDTO) {
        Author newAuthor = new Author(authorDTO);
        return repository.save(newAuthor);
    }

    public Author update(AuthorDTO authorDTO, Long id) {
        Optional<Author> authorOptional = repository.findById(id);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(authorDTO.name());
            Author updatedAuthor = repository.save(author);
            return new Author(updatedAuthor.getId(), updatedAuthor.getName());
        }
        throw new IllegalArgumentException("Id não encontrado");
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
