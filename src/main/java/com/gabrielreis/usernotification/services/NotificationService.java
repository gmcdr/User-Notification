package com.gabrielreis.usernotification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.entities.User;
import com.gabrielreis.usernotification.repositories.UserRepository;

@Service
public class NotificationService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findUsersByEventId () {
    return userRepository.findUsersWithEvents();
  }
}
