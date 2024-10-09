package buteApp.projectBoot.services;


import buteApp.projectBoot.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserService {
    public User findOne(int id);

    public void save(User user);

    public void delete(int id);

    public List<User> findAll();

    public void update(int id, User updatedUser);

}
