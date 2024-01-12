package com.gabrielreis.usernotification.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.gabrielreis.usernotification.entities.Event;
import com.gabrielreis.usernotification.repositories.EventRepository;


@Service
public class EventsService {

  Logger LOG = LoggerFactory.getLogger(EventsService.class);

  @Autowired
  private EventRepository eventRepository;

  public EventsService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public ResponseEntity<Event> saveEvent(@NonNull Event event) {
    eventRepository.save(event);
    LOG.info("Event created.");
    return ResponseEntity.ok(event);
  }

  public ResponseEntity<Event> findEventById(@NonNull Long id) {
    Optional<Event> event = eventRepository.findById(id);
    verifyNullableEvent(event.get());
    return ResponseEntity.ok(event.get());
  }

  public ResponseEntity<HttpStatus> deleteEventById(@NonNull Long id) {
    verifyEventExistence(id);
    eventRepository.deleteById(id);
    LOG.info("Event deleted.");
    return ResponseEntity.ok(HttpStatus.OK);
  }

  public List<Event> findAllEvents() {
    return eventRepository.findAll();
  }

  public Event updateEventById(@NonNull Long id, @NonNull Event event) {
    Optional<Event> currentEvent = eventRepository.findById(id);
    verifyNullableEvent(currentEvent.get());
    Event updatedEvent = currentEvent.get();
    updatedEventBody(event, updatedEvent);
    Objects.requireNonNull(updatedEvent, "updatedEvent is null");
    saveEvent(updatedEvent);
    LOG.info("Event updated.");
    return updatedEvent;
  }

  public void updatedEventBody(Event event, Event updatedEvent){
    updatedEvent.setName(event.getName());
    updatedEvent.setDate(event.getDate());
    updatedEvent.setMessage(event.getMessage());
    updatedEvent.setUsers(event.getUsers());
  }

  public void verifyEventExistence(@NonNull Long id) {
    eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Event not found"));
  }

  public void verifyNullableEvent(Event event) {
    Optional.ofNullable(event).orElseThrow(() -> new IllegalArgumentException("Event cannot be null"));
  }
}
