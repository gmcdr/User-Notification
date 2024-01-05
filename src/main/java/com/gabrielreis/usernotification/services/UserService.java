package com.gabrielreis.usernotification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.entities.User;
import com.gabrielreis.usernotification.repositories.UserRepository;

import ch.qos.logback.classic.Logger;

/**
 * This class represents a service for managing user-related operations.
 * 
 * @author Gabriel Reis
 */
@Service
public class UserService {

  Logger LOG = (Logger) org.slf4j.LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  public UserService() {

  }

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseEntity<User> saveUser(User user) {
    if (userRepository.findById(user.getId()).isPresent()) {
      return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      userRepository.save(user);
      LOG.info("User created.");
    }
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  public ResponseEntity<User> findUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<User>(Optional.of(user).get().orElseThrow(), HttpStatus.OK);
  }

  public ResponseEntity<HttpStatus> deleteUserById(Long id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    } else{
      return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
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
