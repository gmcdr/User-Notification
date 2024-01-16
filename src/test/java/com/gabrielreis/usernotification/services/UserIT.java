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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gabrielreis.usernotification.entities.User;

/**
 * Integration test class for the User service.
 * 
 * @author Gabriel Reis.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIT {

  @Autowired
  private TestRestTemplate restTemplate;

  private Long id = 1L;

  User user = new User(id, "Gabriel", "gabriel@gmail.com");

  @Test
  @Order(1)
  public void testSaveUser() {
    ResponseEntity<User> response = restTemplate.postForEntity("/users/save", user, User.class);
    User savedUser = response.getBody();
    assertNotNull(savedUser);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(user.getId(), savedUser.getId());
    assertEquals(user.getName(), savedUser.getName());
    assertEquals(user.getEmail(), savedUser.getEmail());
  }

  @Test
  @Order(2)
  public void testFindUserById() {
    ResponseEntity<User> response = restTemplate.getForEntity("/users/getById/1", User.class);
    User searchedUser = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(searchedUser);
    assertEquals(user.getId(), searchedUser.getId());
    assertEquals(user.getName(), searchedUser.getName());
    assertEquals(user.getEmail(), searchedUser.getEmail());
  }

  @Test
  @Order(3)
  public void testFindUserByIdNotFound() {
    ResponseEntity<User> response = restTemplate.getForEntity("/users/getById/33", User.class);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @Order(4)
  public void testFindAllUsers() {
    ResponseEntity<User[]> response = restTemplate.getForEntity("/users/getAll", User[].class);
    User[] users = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(users);
    assertEquals(1, users.length);
    assertEquals(user.getId(), users[0].getId());
    assertEquals(user.getName(), users[0].getName());
    assertEquals(user.getEmail(), users[0].getEmail());
  }

  @Test
  @Order(5)
  public void testUpdateUserById() {
    User newUser = new User("Mary", "mary@gmail.com");
    ResponseEntity<User> response = restTemplate.exchange("/users/updateById/1", HttpMethod.PUT, new HttpEntity<>(newUser), User.class);
    User updatedUser = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(updatedUser);
    assertEquals(newUser.getName(), updatedUser.getName());
    assertEquals(newUser.getEmail(), updatedUser.getEmail());
  }

  @Test
  @Order(6)
  public void testDeleteUserById() {
    ResponseEntity<HttpStatus> response = restTemplate.exchange("/users/deleteById/1", HttpMethod.DELETE, null, HttpStatus.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
