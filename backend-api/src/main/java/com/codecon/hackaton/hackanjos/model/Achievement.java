package com.codecon.hackaton.hackanjos.model;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    LocalDateTime achievedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
