package com.gabrielreis.usernotification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.dto.NotificationDTO;


@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(NotificationDTO notificationDTO) {
    SimpleMailMessage notification = new SimpleMailMessage();
    notification.setTo(notificationDTO.getEmail());
    notification.setSubject(notificationDTO.getTitle());
    notification.setText(notificationDTO.getMessage());
    javaMailSender.send(notification);
  }

}
