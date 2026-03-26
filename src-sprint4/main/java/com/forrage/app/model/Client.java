package com.forrage.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer idClient;

    private String nom;
    private String contact;

    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
