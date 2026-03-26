package com.forrage.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "etude")
public class Etude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etude")
    private Integer idEtude;

    @OneToOne
    @JoinColumn(name = "id_demande")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_devis")
    private Devis devis;

    @Column(name = "date_etude")
    private LocalDate dateEtude;

    private String resultat; // 'faisable', 'non_faisable'
    private String commentaire;

    public Integer getIdEtude() { return idEtude; }
    public void setIdEtude(Integer idEtude) { this.idEtude = idEtude; }
    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
    public Devis getDevis() { return devis; }
    public void setDevis(Devis devis) { this.devis = devis; }
    public LocalDate getDateEtude() { return dateEtude; }
    public void setDateEtude(LocalDate dateEtude) { this.dateEtude = dateEtude; }
    public String getResultat() { return resultat; }
    public void setResultat(String resultat) { this.resultat = resultat; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}
