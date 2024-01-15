package com.gabrielreis.usernotification.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class EventMessage {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  private Long id;
  private Long userID;
  private Long eventID;
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "sent_at", nullable = false, updatable = false)
  private Date date;

  public EventMessage(Long userID, Long eventID) {
    this.userID = userID;
    this.eventID = eventID;
  }

}
