package com.codecon.hackaton.hackanjos.repository;

import com.codecon.hackaton.hackanjos.dto.response.ranking.AllKeyPressedResponseDTO;
import com.codecon.hackaton.hackanjos.dto.response.ranking.KeyPressedByKeyResponseDTO;
import com.codecon.hackaton.hackanjos.model.KeyPressed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface KeyPressedRepository extends JpaRepository<KeyPressed, Long> {

    KeyPressed getKeyPressedByKeyCodeAndUserIdAndEventDateBetween(String keyCode, Long userId, LocalDateTime eventDate, LocalDateTime eventDate2);

    @Query("SELECT new com.codecon.hackaton.hackanjos.dto.response.ranking.KeyPressedByKeyResponseDTO(SUM(k.eventCounter), k.user.name, k.user.email, k.user.picture) " +
            "FROM KeyPressed k WHERE k.keyCode = :key AND k.eventDate > :localDateTime GROUP BY k.user ORDER BY SUM(k.eventCounter) DESC")
    Page<KeyPressedByKeyResponseDTO> findAllByKeyCodeAndEventDateAfterOrderByEventCounterDesc(String key, LocalDateTime localDateTime, Pageable pageable);

    @Query("SELECT new com.codecon.hackaton.hackanjos.dto.response.ranking.AllKeyPressedResponseDTO(SUM(k.eventCounter), k.user.name, k.user.email, k.user.picture) " +
            "FROM KeyPressed k WHERE k.eventDate > :localDateTime GROUP BY k.user ORDER BY SUM(k.eventCounter) DESC")
    Page<AllKeyPressedResponseDTO> sumEventCounterGroupByUserId(LocalDateTime localDateTime, Pageable pageable);
}
