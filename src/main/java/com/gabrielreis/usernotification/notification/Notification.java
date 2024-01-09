package com.gabrielreis.usernotification.notification;


import org.springframework.beans.factory.annotation.Autowired;

import com.gabrielreis.usernotification.services.NotificationService;

public class Notification {
  
  @Autowired
  private NotificationService notificationService;

  public Object getUsersWithNotifications() {
    return notificationService.findNotifications();
  }


}
