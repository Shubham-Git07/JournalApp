package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.User;
import com.demo.journalApp.Repository.UserRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
