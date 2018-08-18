package dkpc.projects.presupuestos.app.service;

import dkpc.projects.presupuestos.app.model.User;

import java.util.List;

public interface UserService {

    // Save users
    User save(User user);

    // Get all users
    List<User> findAll();

    // Delete user
    void deleteUser(Long id);
}
