package co.edu.javeriana.libreria.repository;

import co.edu.javeriana.libreria.domain.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
}
