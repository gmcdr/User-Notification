package com.gabrielreis.usernotification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielreis.usernotification.entities.User;
import com.gabrielreis.usernotification.services.UserService;

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

  @GetMapping("/getById")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<User> getUserById(@RequestParam Long param) {
    return userService.findUserById(param);
  }

  @GetMapping("/getAll")
  @ResponseStatus(code = HttpStatus.OK)
  public List<User> getAllUsers() {
    return userService.findAllUsers();
  }

  @DeleteMapping("/deleteById")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<HttpStatus> deleteEventById(@RequestParam Long param) {
    return userService.deleteUserById(param);
  }

  @PostMapping("/updateById")
  @ResponseStatus(code = HttpStatus.OK)
  public User updateById(@RequestBody User user) {
      return userService.updateUserById(user.getId(), user);
  }

}
