package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user, List<String> rolesValues);
    void updateUser(User user, List<String> rolesValues);
    void removeUser(int id);
    User getUserById(int id);
    List<User> listUsers();

}
