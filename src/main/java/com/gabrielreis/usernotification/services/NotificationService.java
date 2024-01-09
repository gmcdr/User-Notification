package com.gabrielreis.usernotification.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.repositories.EventRepository;

@Service
public class NotificationService {
  
  @Autowired
  private EventRepository eventsRepository;

  public Object findNotifications () {
    return eventsRepository.findNotifications();
  }
}
