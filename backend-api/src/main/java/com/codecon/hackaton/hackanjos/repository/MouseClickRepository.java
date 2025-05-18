package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.dto.response.ranking.MouseClickDTO;
import com.codecon.hackaton.hackanjos.model.MouseClick;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MouseClickRepository extends JpaRepository<MouseClick, Long> {

    Optional<MouseClick> getMouseClickByUserIdAndEventDateBetween(Long userId, LocalDateTime localStartDateTime, LocalDateTime localEndDateTime);

    @Query("SELECT new com.codecon.hackaton.hackanjos.dto.response.ranking.MouseClickDTO(SUM(m.eventCounter), m.user.name, m.user.email) " +
            "FROM MouseClick m WHERE m.eventDate > :localDateTime GROUP BY m.user ORDER BY SUM(m.eventCounter) DESC")
    Page<MouseClickDTO> sumEventCounterGroupByUserId(LocalDateTime localDateTime, Pageable pageable);
}
