package com.codecon.hackaton.hackanjos.dto.response.ranking;

import com.codecon.hackaton.hackanjos.model.KeyPressed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyPressedByKeyResponseDTO {

    Long eventCounter;

    String userName;

    String userEmail;

    String userPicture;

    public KeyPressedByKeyResponseDTO(KeyPressed keyPressed) {
        this.eventCounter = keyPressed.getEventCounter();
        this.userName = keyPressed.getUser().getName();
        this.userEmail = keyPressed.getUser().getEmail();
        this.userPicture = keyPressed.getUser().getPicture();
    }
}
