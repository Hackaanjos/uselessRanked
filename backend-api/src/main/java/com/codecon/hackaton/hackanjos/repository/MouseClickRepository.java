package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.model.MouseClick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MouseClickRepository extends JpaRepository<MouseClick, Long> {

    Optional<MouseClick> getMouseClickByUserIdAndEventDateBetween(Long userId, LocalDateTime localStartDateTime, LocalDateTime localEndDateTime);
}
