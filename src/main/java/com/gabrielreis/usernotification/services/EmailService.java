package com.gabrielreis.usernotification.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gabrielreis.usernotification.dto.NotificationDTO;

@Service
public class EmailService {

  private JavaMailSender javaMailSender;

  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }
  
  public void sendEmail(NotificationDTO notificationDTO) {
    
  }

}
