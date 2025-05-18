package com.codecon.hackaton.hackanjos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AchievementResponseDTO {
    private String name;
    private String description;
    private String type;
    private LocalDateTime achievedAt;
}
