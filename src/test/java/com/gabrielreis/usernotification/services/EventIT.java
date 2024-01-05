package com.gabrielreis.usernotification.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gabrielreis.usernotification.entities.Event;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventIT {

  private Long id = 1L;

  private Event event = new Event(id, "Json Party", LocalDateTime.of(2024, 1, 10, 10, 0, 0), "The beat goes on!");

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @Order(1)
  public void testSaveEvent() {
    ResponseEntity<Event> response = restTemplate.postForEntity("/events/save", event, Event.class);
    Event savedEvent = response.getBody();
    assertNotNull(savedEvent);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(event.getId(), savedEvent.getId());
    assertEquals(event.getName(), savedEvent.getName());
    assertEquals(event.getDate(), savedEvent.getDate());
    assertEquals(event.getMessage(), savedEvent.getMessage());
  }

  @Test
  @Order(2)
  public void testFindEventById() {
    ResponseEntity<Event> response = restTemplate.getForEntity("/events/getById/1", Event.class);
    Event searchedEvent = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(searchedEvent);
    assertEquals(event.getId(), searchedEvent.getId());
    assertEquals(event.getName(), searchedEvent.getName());
    assertEquals(event.getDate(), searchedEvent.getDate());
    assertEquals(event.getMessage(), searchedEvent.getMessage());
  }

  @Test
  @Order(3)
  public void testGetAllEvents() {
    ResponseEntity<Event[]> response = restTemplate.getForEntity("/events/getAll", Event[].class);
    Event[] events = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(events);
    assertEquals(1, events.length);
    assertEquals(event.getId(), events[0].getId());
    assertEquals(event.getName(), events[0].getName());
    assertEquals(event.getDate(), events[0].getDate());
    assertEquals(event.getMessage(), events[0].getMessage());
  }

  @Test
  @Order(4)
  public void updateEventById() {
    Event newEvent = new Event("New Json Party", LocalDateTime.of(2024, 2, 10, 10, 0, 0), "The beat goes on!");
    ResponseEntity<Event> response = restTemplate.postForEntity("/events/updateById/1", newEvent, Event.class);
    Event updatedEvent = response.getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(updatedEvent);
    assertEquals(newEvent.getName(), updatedEvent.getName());
    assertEquals(newEvent.getDate(), updatedEvent.getDate());
    assertEquals(newEvent.getMessage(), updatedEvent.getMessage());
  }

  @Test
  @Order(5)
  public void testDeleteEventById() {
    ResponseEntity<Event> response = restTemplate.exchange("/events/deleteById/1", HttpMethod.DELETE, null, Event.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
