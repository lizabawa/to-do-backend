package com.example.todobackend.service;

import com.example.todobackend.exception.InformationAlreadyExistsException;
import com.example.todobackend.model.User;
import com.example.todobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    Logger log = Logger.getLogger(UserService.class.getName());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userObject) {
        log.info("service calling createUser");
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationAlreadyExistsException("user with email address " + userObject.getEmailAddress() +
                    " already exists");
        }
    }
}
