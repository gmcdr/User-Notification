package com.gabrielreis.usernotification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gabrielreis.usernotification.entities.User;

/**
 * This interface represents a repository for managing User entities.
 * It extends the JpaRepository interface, providing CRUD operations for User entities.
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @author Gabriel Reis.
 */
public interface UserRepository extends JpaRepository<User, Long> {

} 
