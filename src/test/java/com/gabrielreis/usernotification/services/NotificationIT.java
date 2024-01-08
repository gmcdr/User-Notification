package com.gabrielreis.usernotification.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gabrielreis.usernotification.entities.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotificationIT {
  
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @Order(1)
  public void testSendNotifications() {
    ResponseEntity<User[]> users = restTemplate.getForEntity("/notifications/send", User[].class);
    assertNotNull(users.getBody());
    assertEquals(HttpStatus.OK, users.getStatusCode());
    User[] usersList =  users.getBody();

  }


}
