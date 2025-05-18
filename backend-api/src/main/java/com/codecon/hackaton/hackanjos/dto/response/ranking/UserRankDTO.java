package com.codecon.hackaton.hackanjos.dto.response.ranking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRankDTO {
    private String userName;
    private String userEmail;
    private Long keyPressCount;
    private Long mouseClickCount;
    private Long mouseMovementDistance;
}
