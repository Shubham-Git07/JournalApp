package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.User;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long id);

    void createNewUser(User user);

    void updateUserById(Long id, User user);

    void deleteUserById(Long id);

    User findByUserName(String userName);

}
