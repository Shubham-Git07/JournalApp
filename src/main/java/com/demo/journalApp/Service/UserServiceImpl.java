package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.User;
import com.demo.journalApp.Exceptions.UserNotFoundException;
import com.demo.journalApp.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));
    }

    @Override
    public void createNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserById(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found"));
        userRepository.deleteById(existingUser.getId());
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
