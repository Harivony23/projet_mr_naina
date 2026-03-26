package com.forrage.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historique_statut")
public class HistoriqueStatut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_demande")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_statut")
    private Statut statut;

    @Column(name = "date_changement")
    private LocalDateTime dateChangement;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
    public LocalDateTime getDateChangement() { return dateChangement; }
    public void setDateChangement(LocalDateTime dateChangement) { this.dateChangement = dateChangement; }
}
