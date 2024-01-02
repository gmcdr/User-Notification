package com.gabrielreis.usernotification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielreis.usernotification.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> { 

}
