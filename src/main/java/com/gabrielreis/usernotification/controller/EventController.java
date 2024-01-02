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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @GetMapping("/getById")
  @ResponseStatus(code = HttpStatus.OK)
  public Event getEventById(@RequestParam Long param) {
    return eventsService.findEventById(param);
  }

  @GetMapping("/getAll")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Event> getAllEvents() {
    return eventsService.findAllEvents();
  }

  @DeleteMapping("/deleteById")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<HttpStatus> deleteEventById(@RequestParam Long param) {
    return eventsService.deleteEventById(param);
  }

  @PostMapping("/updateById")
  @ResponseStatus(code = HttpStatus.OK)
  public Event updateById(@RequestBody Event entity) {
      return eventsService.updateEventById(entity.getId(), entity);
  }
}
