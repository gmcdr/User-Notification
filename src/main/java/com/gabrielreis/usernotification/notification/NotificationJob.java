package com.gabrielreis.usernotification.notification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabrielreis.usernotification.dto.NotificationDTO;
import com.gabrielreis.usernotification.enums.Endpoint;

import org.springframework.web.client.RestTemplate;

public class NotificationJob {

  @Autowired
  private RestTemplate restTemplate = new RestTemplate();

  public void sendNotification() {
   List<NotificationDTO> notificationDTOs = findNotifications();
   for (NotificationDTO notificationDTO : notificationDTOs) {
     System.out.println("Sending notification to " + notificationDTO.getEmail());
     System.out.println("Title: " + notificationDTO.getTitle());
     System.out.println("Message: " + notificationDTO.getMessage());
     System.out.println("User: " + notificationDTO.getUser());
   }
  }

  public List<NotificationDTO> findNotifications() {
    List<?> notifications = restTemplate.getForObject(Endpoint.PEDING_NOTIFICATION.url, List.class);
    List<NotificationDTO> result = new ArrayList<>();
    if (notifications == null) {
      return result;
    }
    for (int i = 0; i < notifications.size(); i++) {
      LinkedHashMap<?, ?> notification = (LinkedHashMap<?, ?>) notifications.get(i);
      NotificationDTO notificationDTO = new NotificationDTO(
        notification.get("title").toString(),
        notification.get("message").toString(), 
        notification.get("user").toString(),
        notification.get("email").toString());
        result.add(notificationDTO);
    }
    return result;
  }
}
