package buteApp.projectBoot.services;

import buteApp.projectBoot.models.User;
import buteApp.projectBoot.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserRepository peopleRepository;

    @Autowired
    public UserServiceImp(UserRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<User> findAll() {
        return peopleRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Transactional
    public void save(User person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, User updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
