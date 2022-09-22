package co.edu.javeriana.libreria.service;

import co.edu.javeriana.libreria.domain.Editorial;
import co.edu.javeriana.libreria.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialService {

    @Autowired
    EditorialRepository editorialRepository;

    public List<Editorial> findAllEditorials() {
        return editorialRepository.findAll();
    }

    public void saveEditorial(Editorial editorial) {
        editorialRepository.save(editorial);
    }

    public void deleteEditorial(int id) {
        editorialRepository.deleteById(id);
    }

    public void updateEditorial(Editorial editorial) {
        editorialRepository.save(editorial);
    }
}
