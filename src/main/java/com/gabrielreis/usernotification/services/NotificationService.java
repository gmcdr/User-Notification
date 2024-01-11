package com.gabrielreis.usernotification.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.dto.NotificationDTO;
import com.gabrielreis.usernotification.repositories.EventRepository;

@Service
public class NotificationService {

  @Autowired
  private EventRepository eventsRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  public void deleteNotifications() {
    eventsRepository.deleteAll();
  }

  public void sendNotification() {
    List<NotificationDTO> notificationDTOs = findNotifications();
    for (NotificationDTO notificationDTO : notificationDTOs) {
      sendEmail(notificationDTO);
    }
  }

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

  public void sendEmail(NotificationDTO notificationDTO) {
    SimpleMailMessage notification = new SimpleMailMessage();
    notification.setTo(notificationDTO.getEmail());
    notification.setSubject(notificationDTO.getTitle());
    notification.setText(notificationDTO.getMessage());
    javaMailSender.send(notification);
  }
}
