package com.codecon.hackaton.hackanjos.dto.response.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MouseMovementResponseDTO {

    Long eventCounter;

    String userName;

    String userEmail;
}
