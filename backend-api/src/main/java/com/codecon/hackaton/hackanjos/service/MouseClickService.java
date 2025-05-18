package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.dto.reponse.ranking.MouseClickDTO;
import com.codecon.hackaton.hackanjos.model.MouseClick;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.repository.MouseClickRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MouseClickService {

    private MouseClickRepository mouseClickRepository;

    public void saveOrUpdateEvent(Long eventCounter, User user) {
        LocalDateTime localStartDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime localEndDateTime = LocalDate.now().atTime(23, 59, 59);
        Optional<MouseClick> mouseClick = mouseClickRepository.getMouseClickByUserIdAndEventDateBetween(user.getId(), localStartDateTime, localEndDateTime);

        if (mouseClick.isEmpty()) {
            save(eventCounter, user);
        } else {
            update(mouseClick.orElse(null), eventCounter);
        }
    }

    public Page<MouseClickDTO> listAll(IntervalFilter intervalFilter, Pageable pageable) {
        LocalDateTime localDateTime = IntervalFilter.getLocalDateTimeByIntervalFilter(intervalFilter);

        return mouseClickRepository.sumEventCounterGroupByUserId(localDateTime, pageable);

    }

    private void save(Long eventCounter, User user) {
        MouseClick mouseClick = new MouseClick();
        mouseClick.setEventCounter(eventCounter);
        mouseClick.setEventDate(LocalDateTime.now());
        mouseClick.setUser(user);

        mouseClickRepository.save(mouseClick);
    }

    private void update(MouseClick mouseClick, Long eventCounter) {
        mouseClick.setEventCounter(mouseClick.getEventCounter() + eventCounter);
        mouseClick.setEventDate(LocalDateTime.now());
        mouseClickRepository.save(mouseClick);
    }
}
