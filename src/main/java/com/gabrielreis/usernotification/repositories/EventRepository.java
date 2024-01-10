package com.gabrielreis.usernotification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielreis.usernotification.entities.Event;

/**
 * This interface represents a repository for managing Event entities.
 * It extends the JpaRepository interface, providing CRUD operations for Event entities.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @author Gabriel Reis.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

  @Query("SELECT e.message, e.name, u.email, u.name FROM Event e INNER JOIN e.users u WHERE e.date = CURRENT_DATE")
  List<Object> findNotifications();
}
