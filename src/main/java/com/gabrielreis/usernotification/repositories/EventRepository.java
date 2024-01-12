package com.gabrielreis.usernotification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielreis.usernotification.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query("SELECT e.message, e.name, u.email, u.name FROM Event e INNER JOIN e.users u WHERE e.date = CURRENT_DATE")
  List<Object> findNotifications();
}
