package com.example.day_planner.service;

import com.example.day_planner.model.User;
import com.example.day_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(updatedUser.getEmail());
                    user.setName(updatedUser.getName());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
