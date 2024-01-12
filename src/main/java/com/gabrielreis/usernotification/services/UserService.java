package com.gabrielreis.usernotification.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.entities.User;
import com.gabrielreis.usernotification.repositories.UserRepository;

@Service
public class UserService {

  Logger LOG = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseEntity<User> saveUser(@NonNull User user) {
    userRepository.save(user);
    LOG.info("User created.");
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  public ResponseEntity<User> findUserById(@NonNull Long id) {
    Optional<User> user = userRepository.findById(id);
    verifyNullableUser(user.get());
    return ResponseEntity.ok(user.get());
  }

  public ResponseEntity<HttpStatus> deleteUserById(@NonNull Long id) {
    verifyUserExistence(id);
    userRepository.deleteById(id);
    LOG.info("User deleted.");
    return ResponseEntity.ok(HttpStatus.OK);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public User updateUserById(@NonNull Long id, @NonNull User user) {
    Optional<User> currentUser = userRepository.findById(id);
    verifyNullableUser(currentUser.get());
    User updatedUser = currentUser.get();
    updatedUserBody(user, updatedUser);
    Objects.requireNonNull(updatedUser, "currentUser is null");
    userRepository.save(updatedUser);
    return currentUser.get();
  }

  public void updatedUserBody(User user, User updatedUser) {
    updatedUser.setName(user.getName());
    updatedUser.setEmail(user.getEmail());
    updatedUser.setEvent(user.getEvent());
  }

  public void verifyUserExistence(@NonNull Long id) {
    userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public void verifyNullableUser(User user) {
    Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("User cannot be null"));
  }
}
