package com.gabrielreis.usernotification.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gabrielreis.usernotification.entities.Event;
import com.gabrielreis.usernotification.services.EventsService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This class is responsible for handling HTTP requests related to events.
 * 
 * @author Gabriel Reis.
 */
@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventsService eventsService;

  @PostMapping("/save")
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
    return eventsService.saveEvent(event);
  }

  @GetMapping("/getById/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Event> getEventById(@PathVariable Long id) {
    return eventsService.findEventById(id);
  }

  @GetMapping("/getAll")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Event> getAllEvents() {
    return eventsService.findAllEvents();
  }

  @DeleteMapping("/deleteById/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<HttpStatus> deleteEventById(@PathVariable Long id) {
    return eventsService.deleteEventById(id);
  }

  @PutMapping("/updateById/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Event updateById(@PathVariable Long id,@RequestBody Event entity) {
    return eventsService.updateEventById(id, entity);
  }
}
