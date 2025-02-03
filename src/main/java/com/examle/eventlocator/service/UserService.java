package com.examle.eventlocator.service;

import com.examle.eventlocator.entity.User;
import com.examle.eventlocator.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Register a new user
    @Transactional
    public User registerUser(User user){
        return userRepository.save(user);
    }

    //Find user by ID
    public Optional<User> findUserById(UUID userId){
        return userRepository.findById(userId);
    }

    //Find user by username
    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    //Update user details
    @Transactional
    public User updateUser(UUID userId, User updatedUser){
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()){
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }
        return null;
    }

    //Delete user by ID
    @Transactional
    public boolean deleteUser(UUID userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean userExistsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean userExistsByName(String username){
        return userRepository.existsByUsername(username);
    }

}
