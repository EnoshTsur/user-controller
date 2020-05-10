package com.enosh.users.controller;

import com.enosh.users.entities.User;
import com.enosh.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// http://localhost:8080/users/

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // http://localhost:8080/users/get/2
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(null));
    }

    // http://localhost:8080/users/get/all
    @GetMapping({"/get/all", "/get/", "/get"})
    public ResponseEntity<Iterable<User>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    // http://localhost:8080/users/create
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user) {
        try {
            return ResponseEntity.ok(repository.save(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    // http://localhost:8080/users/delete/2
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        return repository.findById(id).map(user -> {
            repository.delete(user);
            return ResponseEntity.ok(user);
        }).orElse(ResponseEntity.ok(null));
    }

    // http://localhost:8080/users/update?id=3&email=koop
    @PutMapping("/update")
    public ResponseEntity update(@RequestParam("id") Long id, @RequestParam("email") String email) {
        return repository.findById(id).map(user -> {
            user.setEmail(email);
            return ResponseEntity.ok(repository.save(user));
        }).orElse(ResponseEntity.ok(null));
    }

}

