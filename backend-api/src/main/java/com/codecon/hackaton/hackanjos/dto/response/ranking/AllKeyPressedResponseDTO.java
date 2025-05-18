package com.codecon.hackaton.hackanjos.dto.response.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllKeyPressedResponseDTO {

    Long eventCounter;

    String userName;

    String userEmail;
}
