package com.gabrielreis.usernotification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gabrielreis.usernotification.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u JOIN u.event e")
  List<User> findUsersWithEvents();

}
