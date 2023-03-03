package edu.ntnu.idatt2105.callumg.service;

import edu.ntnu.idatt2105.callumg.model.User;

public interface UserService {
    User saveUser(User user);

    User getUser(String username);

    User updateUser(User user, String username);

    void deleteUser(String username);
}
