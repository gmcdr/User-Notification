package com.gabrielreis.usernotification.notification;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Notification {

  NotificationJob notificationJob = new NotificationJob();

  @Scheduled(fixedRate = 60000)
  public void sendNotification() {
    notificationJob.sendNotification();
  }
}
