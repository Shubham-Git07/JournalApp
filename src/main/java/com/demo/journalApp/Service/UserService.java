package com.demo.journalApp.Service;

import com.demo.journalApp.Entity.User;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
}
