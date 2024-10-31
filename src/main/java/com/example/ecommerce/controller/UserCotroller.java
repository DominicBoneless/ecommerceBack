package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")

public class UserCotroller {
    
    @Autowired
    private final UserService userService;

    public UserCotroller(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<User>> getUserById(@RequestParam Long id) {
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<User>> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/password")
    public Boolean checkPassword(@RequestBody User user) {
        String password = userService.getUserByEmail(user.getEmail()).get().getPassword();
        return password.equals(user.getPassword());
    }
    
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userAux = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAux);
    }
    
    @DeleteMapping
    public ResponseEntity<Optional<User>> deleteUser(@RequestParam Long id){
        Optional<User> user = userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }
    
}
