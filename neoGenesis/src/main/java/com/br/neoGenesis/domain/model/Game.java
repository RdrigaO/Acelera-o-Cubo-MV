package com.br.neoGenesis.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.rmi.server.UID;

@Entity
@Table(name = "GAME")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UID id;

    public String title;
    public String platform;
    public double priceRent;
    public boolean disponible;
}
