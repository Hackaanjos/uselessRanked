package com.codecon.hackaton.hackanjos.model.enums;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public enum AchievementName {

    BABY_STEPS("Baby steps", "Inseriu suas primeiras métricas", 1, "Geral"),
    MISS_CLICK("Miss click?", "Clicou pela primeira vez", 1, "Mouse"),
    CUNEIFORM("Escrita cuneiforme", "Os livros de história dizem que os Sumérios inventaram a escrita, mas nós sabemos que foi você!", 1, "Mouse"),
    XIQUE_XIQUE("Xique-xique, Bahia", "Você mexeu tanto o mouse, que chegou em Xique-Xique, Bahia", 1494000, "Mouse"),
    EIFEEL_TOWER("Mais alto que a Torre Eiffel", "Você scrollou tanto que chegou no topo da Torre Eiffel", 312, "Mouse"),
    HOLY_BIBLE("A Bíblia Sagrada", "Já digitou mais letras do que tem na Bíblia", 400000, "Teclado");

    private String name;
    private String description;
    private Integer quantityToAchieve;
    private String type;

    AchievementName(String name, String description, Integer quantityToAchieve, String type) {
        this.name = name;
        this.description = description;
        this.quantityToAchieve = quantityToAchieve;
        this.type = type;
    }
}
