package com.gabrielreis.usernotification.entities;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  private Long id;
  private String name;
  private String email;
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
