package com.gabrielreis.usernotification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gabrielreis.usernotification.entities.User;

/**
 * This interface represents a repository for managing User entities.
 * It extends the JpaRepository interface, providing CRUD operations for User
 * entities.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @author Gabriel Reis.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u JOIN u.event e")
  List<User> findUsersWithEvents();

}
