package com.forrage.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statut")
    private Integer idStatut;

    @Column(unique = true)
    private String code;
    private String libelle;
    private String categorie;

    public Integer getIdStatut() { return idStatut; }
    public void setIdStatut(Integer idStatut) { this.idStatut = idStatut; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
}
