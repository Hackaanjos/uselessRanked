package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.dto.response.ranking.MouseMovementResponseDTO;
import com.codecon.hackaton.hackanjos.model.MouseMovement;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.repository.MouseMovementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MouseMovementService {

    private MouseMovementRepository mouseMovementRepository;
    private AchievementService achievementService;

    public void saveOrUpdateEvent(Long distance, User user) {
        LocalDateTime localStartDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime localEndDateTime = LocalDate.now().atTime(23, 59, 59);

        MouseMovement mouseMovement = mouseMovementRepository.getMouseMovementByUserIdAndEventDateBetween(user.getId(), localStartDateTime, localEndDateTime);
        if (mouseMovement == null) {
            MouseMovement newMouseMovement = save(distance, user);
            achievementService.triggerMouseMovementAchievement(user, newMouseMovement.getDistance());
        } else {
            mouseMovement = update(mouseMovement, distance);
            achievementService.triggerMouseMovementAchievement(user, mouseMovement.getDistance());
        }
    }

    public Page<MouseMovementResponseDTO> listAll(IntervalFilter intervalFilter, Pageable pageable) {
        LocalDateTime localDateTime = IntervalFilter.getLocalDateTimeByIntervalFilter(intervalFilter);

        return mouseMovementRepository.sumDistanceGroupByUserId(localDateTime, pageable);
    }

    private MouseMovement save(Long distance, User user) {
        MouseMovement mouseMovement = new MouseMovement();
        mouseMovement.setDistance(distance);
        mouseMovement.setEventDate(LocalDateTime.now());
        mouseMovement.setUser(user);

        return mouseMovementRepository.save(mouseMovement);
    }

    private MouseMovement update(MouseMovement mouseMovement, Long distance) {
        mouseMovement.setDistance(mouseMovement.getDistance() + distance);
        return mouseMovementRepository.save(mouseMovement);
    }
}
