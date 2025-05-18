package com.codecon.hackaton.hackanjos.controller.ranking;

import com.codecon.hackaton.hackanjos.dto.response.ranking.UserRankDTO;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.repository.KeyPressedRepository;
import com.codecon.hackaton.hackanjos.repository.MouseClickRepository;
import com.codecon.hackaton.hackanjos.repository.MouseMovementRepository;
import com.codecon.hackaton.hackanjos.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/ranking/user")
@AllArgsConstructor
public class UserRankingController {

    private final KeyPressedRepository keyPressedRepository;
    private final MouseClickRepository mouseClickRepository;
    private final MouseMovementRepository mouseMovementRepository;
    private final UserRepository userRepository;

    @GetMapping("/{intervalFilterString}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserRankDTO> getUserMetrics(@AuthenticationPrincipal OAuth2User oAuth2User, @PathVariable String intervalFilterString) {
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(null);
        }

        IntervalFilter intervalFilter = IntervalFilter.fromString(intervalFilterString);
        LocalDateTime startDateTime = IntervalFilter.getLocalDateTimeByIntervalFilter(intervalFilter);
        LocalDateTime endDateTime = LocalDateTime.now();

        Long totalKeyPresses = keyPressedRepository.findAll().stream()
                .filter(kp -> kp.getUser().getId().equals(user.getId()) &&
                        kp.getEventDate().isAfter(startDateTime) &&
                        kp.getEventDate().isBefore(endDateTime))
                .mapToLong(kp -> kp.getEventCounter())
                .sum();

        Long totalMouseClicks = mouseClickRepository.findAll().stream()
                .filter(mc -> mc.getUser().getId().equals(user.getId()) &&
                        mc.getEventDate().isAfter(startDateTime) &&
                        mc.getEventDate().isBefore(endDateTime))
                .mapToLong(mc -> mc.getEventCounter())
                .sum();

        Long totalMouseDistance = mouseMovementRepository.findAll().stream()
                .filter(mc -> mc.getUser().getId().equals(user.getId()) &&
                        mc.getEventDate().isAfter(startDateTime) &&
                        mc.getEventDate().isBefore(endDateTime))
                .mapToLong(mc -> mc.getDistance())
                .sum();

        UserRankDTO userRankDTO = UserRankDTO.builder()
                .userName(user.getName())
                .userEmail(user.getEmail())
                .userPicture(user.getPicture())
                .keyPressCount(totalKeyPresses)
                .mouseClickCount(totalMouseClicks)
                .mouseMovementDistance(totalMouseDistance)
                .build();

        return ResponseEntity.ok(userRankDTO);
    }
}
