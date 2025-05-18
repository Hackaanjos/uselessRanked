package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.dto.response.ranking.MouseClickDTO;
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
    private AchievementService achievementService;

    public void saveOrUpdateEvent(Long eventCounter, User user) {
        LocalDateTime localStartDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime localEndDateTime = LocalDate.now().atTime(23, 59, 59);
        Optional<MouseClick> mouseClick = mouseClickRepository.getMouseClickByUserIdAndEventDateBetween(user.getId(), localStartDateTime, localEndDateTime);

        if (mouseClick.isEmpty()) {
            MouseClick newMouseClick = save(eventCounter, user);
            achievementService.triggerMouseClickAchievement(user, newMouseClick);
        } else {
            MouseClick updatedMouseClick = update(mouseClick.orElse(null), eventCounter);

            achievementService.triggerMouseClickAchievement(user, updatedMouseClick);
        }
    }

    public Page<MouseClickDTO> listAll(IntervalFilter intervalFilter, Pageable pageable) {
        LocalDateTime localDateTime = IntervalFilter.getLocalDateTimeByIntervalFilter(intervalFilter);

        return mouseClickRepository.sumEventCounterGroupByUserId(localDateTime, pageable);
    }

    private MouseClick save(Long eventCounter, User user) {
        MouseClick mouseClick = new MouseClick();
        mouseClick.setEventCounter(eventCounter);
        mouseClick.setEventDate(LocalDateTime.now());
        mouseClick.setUser(user);

        return mouseClickRepository.save(mouseClick);
    }

    private MouseClick update(MouseClick mouseClick, Long eventCounter) {
        mouseClick.setEventCounter(mouseClick.getEventCounter() + eventCounter);
        return mouseClickRepository.save(mouseClick);
    }
}
