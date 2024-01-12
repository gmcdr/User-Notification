package com.gabrielreis.usernotification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielreis.usernotification.services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
  
  @Autowired
  private NotificationService notificationService;

  @PostMapping("/send")
  public void sendNotifications() {
    notificationService.sendNotification();
  }
}
