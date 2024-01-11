package com.gabrielreis.usernotification.notification;

import org.springframework.web.client.RestTemplate;

public class NotificationJob {

  RestTemplate restTemplate = new RestTemplate();
  
  public void execute() {
    restTemplate.postForObject("http://localhost:8080/notifications/send", null, Object.class);
  }

}
