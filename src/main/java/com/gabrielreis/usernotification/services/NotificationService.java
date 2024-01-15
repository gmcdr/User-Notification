package com.gabrielreis.usernotification.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.dto.NotificationDTO;
import com.gabrielreis.usernotification.entities.EventMessage;
import com.gabrielreis.usernotification.repositories.EventMessageRepository;
import com.gabrielreis.usernotification.repositories.EventRepository;

@Service
public class NotificationService {

  Logger LOG = LoggerFactory.getLogger(NotificationService.class);

  @Autowired
  private EventRepository eventsRepository;

  @Autowired
  private EventMessageRepository messageRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  public void deleteNotifications() {
    eventsRepository.deleteAll();
  }

  public void sendNotification() {
    List<NotificationDTO> notificationDTOs = findNotifications();
    for (NotificationDTO notificationDTO : notificationDTOs) {
      if (!verifyMessageAlredySent(notificationDTO)) {
        sendEmail(notificationDTO);
        LOG.info("Email sent to: " + notificationDTO.email());
      } else {
        LOG.info("Email already sent to: " + notificationDTO.email());
      }
    }
  }

  public boolean verifyMessageAlredySent(NotificationDTO notificationDTO) {
    Long result = messageRepository.findMessage(notificationDTO.eventID(), notificationDTO.userID());
    if (result != null) {
      return true;
    }
    return false;
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
        notificationArray[2].toString(),
        Long.parseLong(notificationArray[5].toString()),
        Long.parseLong(notificationArray[4].toString()));
      result.add(notificationDTO);
    }
    return result;
  }

  public void sendEmail(NotificationDTO notificationDTO) {
    boolean success = false;
    try {
      SimpleMailMessage notification = buildSimpleMailMessage(notificationDTO);
      Objects.requireNonNull(notification, "notification is null");
      javaMailSender.send(notification);
      success = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (success) {
      saveMessage(notificationDTO);
    }
  }

  public SimpleMailMessage buildSimpleMailMessage(NotificationDTO notificationDTO) {
    SimpleMailMessage notification = new SimpleMailMessage();
    notification.setTo(notificationDTO.email());
    notification.setSubject(notificationDTO.title());
    notification.setText(notificationDTO.message());
    return notification;
  }

  public void saveMessage(NotificationDTO notificationDTO) {
    messageRepository.save(new EventMessage(notificationDTO.userID(), notificationDTO.eventID()));
  }
}
