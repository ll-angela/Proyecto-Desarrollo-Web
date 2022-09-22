package co.edu.javeriana.libreria.service;

import co.edu.javeriana.libreria.domain.User;
import co.edu.javeriana.libreria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void inactivate(int idUser) {
        User auxUser = userRepository.findById(idUser).get();
        auxUser.setActive(false);
        userRepository.save(auxUser);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
