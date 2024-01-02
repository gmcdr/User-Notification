package com.gabrielreis.usernotification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  private Long id;
  private String name;
  private String email;
  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  public User() {

  }

  public User(String name, String email, Event event) {
    this.name = name;
    this.email = email;
    this.event = event;
  }

  public Long getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }
}
