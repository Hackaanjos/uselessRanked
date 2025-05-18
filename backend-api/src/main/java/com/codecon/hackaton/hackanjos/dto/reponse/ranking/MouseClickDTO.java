package com.codecon.hackaton.hackanjos.dto.reponse.ranking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MouseClickDTO {

    Long eventCounter;

    String userName;

    String userEmail;
}
