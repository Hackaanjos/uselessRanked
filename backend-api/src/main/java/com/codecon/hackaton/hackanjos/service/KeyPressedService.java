package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.dto.response.ranking.AllKeyPressedResponseDTO;
import com.codecon.hackaton.hackanjos.dto.response.ranking.KeyPressedByKeyResponseDTO;
import com.codecon.hackaton.hackanjos.model.KeyPressed;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.repository.KeyPressedRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class KeyPressedService {

    private KeyPressedRepository keyPressedRepository;

    public void saveOrUpdateEvent(String keyCode, Long eventCounter, User user) {
        LocalDateTime localStartDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime localEndDateTime = LocalDate.now().atTime(23, 59, 59);
        KeyPressed keyPressed = keyPressedRepository.getKeyPressedByKeyCodeAndUserIdAndEventDateBetween(keyCode, user.getId(), localStartDateTime, localEndDateTime);

        if (keyPressed == null) {
            save(keyCode, eventCounter, user);
        } else {
            update(keyPressed, eventCounter);
        }
    }

    public Page<KeyPressedByKeyResponseDTO> listByKey(String key, IntervalFilter intervalFilter, Pageable pageable) {
        LocalDateTime localStartDateTime = IntervalFilter.getLocalDateTimeByIntervalFilter(intervalFilter);

        return keyPressedRepository
                .findAllByKeyCodeAndEventDateAfterOrderByEventCounterDesc(key, localStartDateTime, pageable);
    }

    public Page<AllKeyPressedResponseDTO> listAll(IntervalFilter intervalFilter, Pageable pageable) {
        LocalDateTime localDateTime = IntervalFilter.getLocalDateTimeByIntervalFilter(intervalFilter);

        return keyPressedRepository.sumEventCounterGroupByUserId(localDateTime, pageable);
    }

    private void save(String keyCode, Long eventCounter, User user) {
        KeyPressed keyPressed = new KeyPressed();
        keyPressed.setKeyCode(keyCode);
        keyPressed.setEventCounter(eventCounter);
        keyPressed.setEventDate(LocalDateTime.now());
        keyPressed.setUser(user);
        keyPressedRepository.save(keyPressed);
    }

    private void update(KeyPressed keyPressed, Long eventCounter) {
        keyPressed.setEventCounter(keyPressed.getEventCounter() + eventCounter);
        keyPressedRepository.save(keyPressed);
    }
}
