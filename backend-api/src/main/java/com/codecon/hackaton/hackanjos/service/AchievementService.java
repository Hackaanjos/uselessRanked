package com.codecon.hackaton.hackanjos.service;

import com.codecon.hackaton.hackanjos.model.Achievement;
import com.codecon.hackaton.hackanjos.model.MouseClick;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.model.enums.AchievementName;
import com.codecon.hackaton.hackanjos.repository.AchievementRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public void triggerMouseClickAchievement(User user, MouseClick mouseClick) {
        if (mouseClick.getEventCounter() >= 1) {
            saveIfNecessary(user, AchievementName.MISS_CLICK);
        }
    }

    public void triggerMouseMovementAchievement(User user, Long mouseMovementDistance) {
        if (mouseMovementDistance >= AchievementName.XIQUE_XIQUE.getQuantityToAchieve()) {
            saveIfNecessary(user, AchievementName.XIQUE_XIQUE);
        }
    }

    public void triggerKeyPressAchievement(User user, Long keyPressCount) {
        if (keyPressCount >= AchievementName.HOLY_BIBLE.getQuantityToAchieve()) {
            saveIfNecessary(user, AchievementName.HOLY_BIBLE);
        }
    }

    public void saveIfNecessary(User user, AchievementName achievementName) {
        String name = achievementName.name();
        List<Achievement> existingAchievements = achievementRepository.findByUserAndName(user, name);
        if (!existingAchievements.isEmpty()) {
            return;
        }

        Achievement achievement = new Achievement();
        achievement.setName(name);
        achievement.setUser(user);
        achievement.setAchievedAt(LocalDateTime.now());

        achievementRepository.save(achievement);
    }
}
