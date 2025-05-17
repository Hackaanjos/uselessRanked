package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.model.KeyPressed;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.repository.KeyPressedRepository;

import lombok.AllArgsConstructor;

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

    private void save(String keyCode, Long eventCounter, User user) {
        KeyPressed keyPressed;
        keyPressed = new KeyPressed();
        keyPressed.setKeyCode(keyCode);
        keyPressed.setEventCounter(eventCounter);
        keyPressed.setEventDate(LocalDateTime.now());
        keyPressed.setUser(user);
        keyPressedRepository.save(keyPressed);
    }

    private void update(KeyPressed keyPressed, Long eventCounter) {
        keyPressed.setEventCounter(keyPressed.getEventCounter() + eventCounter);
        keyPressed.setEventDate(LocalDateTime.now());
        keyPressedRepository.save(keyPressed);
    }
}
