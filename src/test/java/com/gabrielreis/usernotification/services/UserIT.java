package com.gabrielreis.usernotification.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gabrielreis.usernotification.entities.User;

/**
 * Integration test class for the User service.
 * 
 * @author Gabriel Reis.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {

  @Autowired
  private TestRestTemplate restTemplate;

  private Long id = 1L;

  User user = new User(id, "Gabriel", "gabriel@gmail.com");

  @Test
  public void testSaveUser() {
    ResponseEntity<User> response = restTemplate.postForEntity("/users/save", user, User.class);
    User savedUser = response.getBody();
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    if (savedUser != null) {
      assertEquals(user.getId(), savedUser.getId());
      assertEquals(user.getName(), savedUser.getName());
      assertEquals(user.getEmail(), savedUser.getEmail());
    }
  }

  @Test
  public void testFindUserById() {
    ResponseEntity<User> response = restTemplate.getForEntity("/users/getById/1", User.class);
    User searchedUser = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    if (searchedUser != null) {
      assertEquals(user.getId(), searchedUser.getId());
      assertEquals(user.getName(), searchedUser.getName());
      assertEquals(user.getEmail(), searchedUser.getEmail());
    }
  }

}
