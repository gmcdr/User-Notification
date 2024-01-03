package com.gabrielreis.usernotification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.entities.User;
import com.gabrielreis.usernotification.repositories.UserRepository;

/**
 * This class represents a service for managing user-related operations.
 * 
 * @author Gabriel Reis
 */
@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseEntity<User> saveUser(User user) {
    userRepository.save(user);
    return new ResponseEntity<User>(user, HttpStatus.CREATED); 
  }

  public ResponseEntity<User> findUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return new ResponseEntity<User>(Optional.of(user).get().orElseThrow(), HttpStatus.OK) ;
  }

  public ResponseEntity<HttpStatus> deleteUserById(Long id) {
    userRepository.deleteById(id);
    return new ResponseEntity<HttpStatus>(HttpStatus.OK); 
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public User updateUserById(Long id, User user) {
    Optional<User> currentUser = userRepository.findById(id);
    if (currentUser.isPresent()) {
      currentUser.get().setName(user.getName());
      currentUser.get().setEmail(user.getEmail());
      currentUser.get().setEvent(user.getEvent());
      userRepository.save(currentUser.get());
    }
    return currentUser.get();
  }
}
