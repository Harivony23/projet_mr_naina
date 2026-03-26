package com.forrage.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "travaux")
public class Travaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_travaux")
    private Integer idTravaux;

    @OneToOne
    @JoinColumn(name = "id_demande")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_devis")
    private Devis devis;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_arrivee_eau")
    private LocalDate dateArriveeEau;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    public Integer getIdTravaux() { return idTravaux; }
    public void setIdTravaux(Integer idTravaux) { this.idTravaux = idTravaux; }
    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
    public Devis getDevis() { return devis; }
    public void setDevis(Devis devis) { this.devis = devis; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateArriveeEau() { return dateArriveeEau; }
    public void setDateArriveeEau(LocalDate dateArriveeEau) { this.dateArriveeEau = dateArriveeEau; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
}
