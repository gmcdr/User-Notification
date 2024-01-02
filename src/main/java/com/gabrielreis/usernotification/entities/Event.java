package com.gabrielreis.usernotification.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

  @Id
  Long id;
  private String name;
  private Date date;
  private String message;
  @OneToMany(mappedBy = "event", cascade = { jakarta.persistence.CascadeType.ALL })
  private List<User> users;

  public Event(String name, Date date, String message, List<User> users) {
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
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
