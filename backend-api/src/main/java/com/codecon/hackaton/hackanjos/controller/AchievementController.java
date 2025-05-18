package com.codecon.hackaton.hackanjos.controller;

import com.codecon.hackaton.hackanjos.dto.response.AchievementResponseDTO;
import com.codecon.hackaton.hackanjos.model.Achievement;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.repository.AchievementRepository;
import com.codecon.hackaton.hackanjos.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/achievements")
@AllArgsConstructor
public class AchievementController {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AchievementResponseDTO>> getUserAchievements(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Achievement> achievements = achievementRepository.findByUser(user);

        List<AchievementResponseDTO> achievementDTOs = achievements.stream()
                .map(achievement -> AchievementResponseDTO.builder()
                        .name(achievement.getAchievementName().getName())
                        .description(achievement.getAchievementName().getDescription())
                        .type(achievement.getAchievementName().getType())
                        .achievedAt(achievement.getAchievedAt())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(achievementDTOs);
    }
}
