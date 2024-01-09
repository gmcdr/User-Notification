package com.gabrielreis.usernotification.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.dto.NotificationDTO;
import com.gabrielreis.usernotification.repositories.EventRepository;

@Service
public class NotificationService {

  @Autowired
  private EventRepository eventsRepository;

  public List<NotificationDTO> findNotifications() {
    List<Object> notifications = eventsRepository.findNotifications();
    List<NotificationDTO> result = new ArrayList<>();
    for (Object notification : notifications) {
      Object[] notificationArray = (Object[]) notification;
      NotificationDTO notificationDTO = new NotificationDTO(
        notificationArray[1].toString(),
        notificationArray[0].toString(), 
        notificationArray[3].toString(), 
        notificationArray[2].toString());
        result.add(notificationDTO);
    }
    return result;
  }
}
