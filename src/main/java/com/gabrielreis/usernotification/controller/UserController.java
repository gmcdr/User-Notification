package com.gabrielreis.usernotification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielreis.usernotification.entities.User;
import com.gabrielreis.usernotification.services.UserService;

/**
 * This class represents the controller for managing user-related operations.
 * 
 * @author Gabriel Reis.
 */
@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @PostMapping("/save")
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @GetMapping("/getById/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userService.findUserById(id);
  }

  @GetMapping("/getAll")
  @ResponseStatus(code = HttpStatus.OK)
  public List<User> getAllUsers() {
    return userService.findAllUsers();
  }

  @DeleteMapping("/deleteById/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<HttpStatus> deleteEventById(@PathVariable Long id) {
    return userService.deleteUserById(id);
  }

  @PutMapping("/updateById/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public User updateById(@PathVariable Long id, @RequestBody User user) {
      return userService.updateUserById(id, user);
  }

}
