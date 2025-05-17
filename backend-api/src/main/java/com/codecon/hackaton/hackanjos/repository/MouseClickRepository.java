package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.model.MouseClick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MouseClickRepository extends JpaRepository<MouseClick, Long> {

    MouseClick getMouseClickByUserIdAndEventDateBetween(Long userId, LocalDateTime localStartDateTime, LocalDateTime localEndDateTime);
}
