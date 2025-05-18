package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.model.MouseMovement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MouseMovementRepository extends JpaRepository<MouseMovement, Long> {

    MouseMovement getMouseMovementByUserIdAndEventDateBetween(Long userId, LocalDateTime localStartDateTime, LocalDateTime localEndDateTime);
}
