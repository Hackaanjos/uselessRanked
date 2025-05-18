package com.codecon.hackaton.hackanjos.dto.reponse.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MouseClickDTO {

    Long eventCounter;

    String userName;

    String userEmail;
}
