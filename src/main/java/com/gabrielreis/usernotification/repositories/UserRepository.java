package com.gabrielreis.usernotification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gabrielreis.usernotification.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

} 
