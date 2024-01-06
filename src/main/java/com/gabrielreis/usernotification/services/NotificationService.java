package com.gabrielreis.usernotification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  @Autowired
  private UserService userService;

  NotificationService() {
  
  }

  
}
