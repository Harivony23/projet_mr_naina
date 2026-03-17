package com.bacc.correction.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Correcteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
}
