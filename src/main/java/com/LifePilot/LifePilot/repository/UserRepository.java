package com.LifePilot.LifePilot.repository;

import com.LifePilot.LifePilot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // Basic DB Queries are already there like save, findById, FindALl, deleteById, ExistsById;

    // These are our custom queries

    // find the user using emailid
    Optional<User> findByEmail(String email);

    // email is already exists in DB
    boolean existsByEmail(String email);
}
