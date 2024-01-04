package com.gabrielreis.usernotification.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
public class UserIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  UserService userService;

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
  public void testSaveUserExists() {
    userService.saveUser(user);
    ResponseEntity<User> response = restTemplate.postForEntity("/users/save", user, User.class); 
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void testFindUserById() {
    userService.saveUser(user);
    ResponseEntity<User> response = restTemplate.getForEntity("/users/getById/{id}", User.class, 1L);
    User searchedUser = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    if (searchedUser != null) {
      assertEquals(user.getId(), searchedUser.getId());
      assertEquals(user.getName(), searchedUser.getName());
      assertEquals(user.getEmail(), searchedUser.getEmail());
    }
  }

  @Test
  public void testFindUserByIdNotFound() {
    ResponseEntity<User> response = restTemplate.getForEntity("/users/getById/33", User.class);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void testFindAllUsers() {
    userService.saveUser(user);
    ResponseEntity<User[]> response = restTemplate.getForEntity("/users/getAll", User[].class);
    User[] users = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    if (users != null) {
      assertEquals(1, users.length);
      assertEquals(user.getId(), users[0].getId());
      assertEquals(user.getName(), users[0].getName());
      assertEquals(user.getEmail(), users[0].getEmail());
    }
  }

  @Test
  public void testDeleteUserById() {
    userService.saveUser(user);
    ResponseEntity<HttpStatus> response = restTemplate.exchange("/users/deleteById/{id}",HttpMethod.DELETE,null,HttpStatus.class,1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void testUpdateUserById() {
    userService.saveUser(user);
    ResponseEntity<User> response = restTemplate.postForEntity("/users/updateById/1", user, User.class);
    User updatedUser = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    if (updatedUser != null) {
      assertEquals(user.getId(), updatedUser.getId());
      assertEquals(user.getName(), updatedUser.getName());
      assertEquals(user.getEmail(), updatedUser.getEmail());
    }
  }

}
