package com.gabrielreis.usernotification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabrielreis.usernotification.entities.EventMessage;

public interface EventMessageRepository extends JpaRepository<EventMessage, Long>{
  
  @Query("SELECT m.id from EventMessage m where m.eventID = :eID and m.userID = :uID")
  Long findMessage(@Param("eID") Long eID, @Param("uID") Long uID);
}
