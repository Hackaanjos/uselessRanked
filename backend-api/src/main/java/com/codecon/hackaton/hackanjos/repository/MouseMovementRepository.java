package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.dto.response.ranking.MouseMovementResponseDTO;
import com.codecon.hackaton.hackanjos.model.MouseMovement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MouseMovementRepository extends JpaRepository<MouseMovement, Long> {

    MouseMovement getMouseMovementByUserIdAndEventDateBetween(Long userId, LocalDateTime localStartDateTime, LocalDateTime localEndDateTime);

    @Query("SELECT new com.codecon.hackaton.hackanjos.dto.response.ranking.MouseMovementResponseDTO(SUM(m.distance), m.user.name, m.user.email, m.user.picture) " +
            "FROM MouseMovement m WHERE m.eventDate > :localDateTime GROUP BY m.user ORDER BY SUM(m.distance) DESC")
    Page<MouseMovementResponseDTO> sumDistanceGroupByUserId(LocalDateTime localDateTime, Pageable pageable);
}
