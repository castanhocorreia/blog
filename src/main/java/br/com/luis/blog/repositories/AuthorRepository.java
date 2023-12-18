package br.com.luis.blog.repositories;

import br.com.luis.blog.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
