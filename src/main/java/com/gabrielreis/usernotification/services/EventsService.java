package com.gabrielreis.usernotification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.gabrielreis.usernotification.entities.Event;
import com.gabrielreis.usernotification.repositories.EventRepository;

/**
 * This class represents a service for managing events.
 * 
 * @author Gabriel Reis.
 */
@Service
public class EventsService {

  @Autowired
  private EventRepository eventRepository;

  public EventsService() {

  }

  public EventsService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public ResponseEntity<Event> saveEvent(Event event) {
    if (eventRepository.findById(event.getId()).isPresent()) {
      return new ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      eventRepository.save(event);
      return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }
  }

  public ResponseEntity<Event> findEventById(Long id) {
    Optional<Event> event = eventRepository.findById(id);
    if (!event.isEmpty()) {
      return new ResponseEntity<Event>(event.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<HttpStatus> deleteEventById(Long id) {
    if (eventRepository.findById(id).isPresent()) {
      eventRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
  }

  public List<Event> findAllEvents() {
    return eventRepository.findAll();
  }

  public Event updateEventById(Long id, Event event) {
    Optional<Event> currentEvent = eventRepository.findById(id);
    if (currentEvent.isPresent()) {
      currentEvent.get().setName(event.getName());
      currentEvent.get().setDate(event.getDate());
      currentEvent.get().setMessage(event.getMessage());
      currentEvent.get().setUsers(event.getUsers());
      eventRepository.save(currentEvent.get());
    }
    return currentEvent.get();
  }

}
