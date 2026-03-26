package com.forrage.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "details_devis")
public class DetailsDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Integer idDetail;

    @ManyToOne
    @JoinColumn(name = "id_devis")
    private Devis devis;

    private String libelle;
    private BigDecimal montant;

    public Integer getIdDetail() { return idDetail; }
    public void setIdDetail(Integer idDetail) { this.idDetail = idDetail; }
    public Devis getDevis() { return devis; }
    public void setDevis(Devis devis) { this.devis = devis; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
}
