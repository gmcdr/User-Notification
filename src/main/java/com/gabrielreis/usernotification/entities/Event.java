package com.gabrielreis.usernotification.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents an event in the system.
 * 
 * @author Gabriel Reis.
 */
@Entity
@Table(name = "events")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime date;
  private String message;
  @OneToMany(mappedBy = "event", cascade = { CascadeType.ALL })
  @JsonManagedReference
  private List<User> users;

  public Event() {

  }

  public Event(Long id, String name, LocalDateTime date, String message) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.message = message;
  }

  public Event(String name, LocalDateTime date, String message) {
    this.name = name;
    this.date = date;
    this.message = message;
  }

  public Event(String name, LocalDateTime date, String message, List<User> users) {
    this.name = name;
    this.date = date;
    this.message = message;
    this.users = users;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public Long getId() {
    return id;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

}
