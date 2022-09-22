package co.edu.javeriana.libreria.repository;

import co.edu.javeriana.libreria.domain.Book;
import co.edu.javeriana.libreria.domain.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String name);
    List<Book> findByEditorial(String name);
}
