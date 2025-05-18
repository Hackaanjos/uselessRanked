package com.codecon.hackaton.hackanjos.dto.response.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MouseMovementResponseDTO {

    Long distance;

    String userName;

    String userEmail;
}
