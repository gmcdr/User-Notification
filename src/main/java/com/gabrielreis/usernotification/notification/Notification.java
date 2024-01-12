package com.gabrielreis.usernotification.notification;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gabrielreis.usernotification.enums.Endpoints;
import com.gabrielreis.usernotification.interfaces.Job;

@Component
public class Notification implements Job{

  Logger LOG = LoggerFactory.getLogger(Notification.class);

  RestTemplate restTemplate = new RestTemplate();
  final String URL = Endpoints.SEND_NOTIFICATION.url;

  @Scheduled(fixedRate = 600000)
  public void sendNotification() {
    execute();
  }

  @Override
  public void execute() {
    Objects.requireNonNull(restTemplate, "restTemplate is null");
    Objects.requireNonNull(URL, "URL is null");
    restTemplate.postForObject(URL, Object.class, Object.class);
    LOG.info("Notification sent");
  }
}
