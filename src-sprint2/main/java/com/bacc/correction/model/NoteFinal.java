package com.bacc.correction.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notefinal")
public class NoteFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;

    @Column(name = "notefinal")
    private Double noteFinal;
}
