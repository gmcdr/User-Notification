package com.gabrielreis.usernotification.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "events")
@Data
public class Event {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column(name = "event_name")
  private String name;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;
  private String message;
  @OneToMany(mappedBy = "event", cascade = { CascadeType.ALL })
  @JsonManagedReference
  private List<User> users;

}
