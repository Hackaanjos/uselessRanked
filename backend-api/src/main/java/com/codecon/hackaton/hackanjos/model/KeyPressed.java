package com.codecon.hackaton.hackanjos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table
public class KeyPressed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min=1, max=1)
    @Column(nullable = false)
    String keyCode;

    @Column(nullable = false)
    Long eventCounter;

    @Column(nullable = false)
    LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
