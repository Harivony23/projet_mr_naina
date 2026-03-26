package com.forrage.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_devis")
public class TypeDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer idType;

    @Column(unique = true)
    private String libelle;

    public Integer getIdType() { return idType; }
    public void setIdType(Integer idType) { this.idType = idType; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}
