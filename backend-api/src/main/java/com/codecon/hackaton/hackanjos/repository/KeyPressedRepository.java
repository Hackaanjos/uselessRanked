package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.model.KeyPressed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface KeyPressedRepository extends JpaRepository<KeyPressed, Long> {

    KeyPressed getKeyPressedByKeyCodeAndUserIdAndEventDateBetween(String keyCode, Long userId, LocalDateTime eventDate, LocalDateTime eventDate2);

    Page<KeyPressed> findAllByKeyCodeOrderByEventCounterDesc(String key, Pageable pageable);
}
