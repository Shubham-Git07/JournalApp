package com.demo.journalApp.Controller;

import com.demo.journalApp.Entity.User;
import com.demo.journalApp.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        try {
            userService.createNewUser(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("user not created", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUserById/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id, @RequestBody User user) {
        try {
            userService.updateUserById(id, user);
            return ResponseEntity.ok("User updated successfully");
        }
        catch (IllegalArgumentException e) { // Username already exists
            return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) { // User not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/updateUserByUserName")
    public ResponseEntity<?> updateUserByUsername(@RequestBody User user) {
        User userInDB = userService.findByUserName(user.getUserName());
        if (userInDB == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Optional: Check if new username is taken
        if (!user.getUserName().equals(userInDB.getUserName()) &&
                userService.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.updateUserById(userInDB.getId(), userInDB);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("user deleted successfully", HttpStatus.NO_CONTENT);
    }

}
