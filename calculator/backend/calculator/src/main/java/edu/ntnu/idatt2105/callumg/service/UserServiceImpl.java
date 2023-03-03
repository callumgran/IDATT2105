package edu.ntnu.idatt2105.callumg.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.ntnu.idatt2105.callumg.model.User;
import edu.ntnu.idatt2105.callumg.repository.UserRepository;

public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) throws NullPointerException {
        return userRepository.findById(username).orElseThrow(() -> new NullPointerException("User not found"));
    }

    @Override
    public User updateUser(User user, String username) throws NullPointerException {
        User userToUpdate = getUser(username);
        userToUpdate.setCalculations(user.getCalculations());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String username) {

        userRepository.deleteById(username);
    }
}
