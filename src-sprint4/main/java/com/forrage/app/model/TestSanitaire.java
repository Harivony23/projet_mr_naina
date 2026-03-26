package com.forrage.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "test_sanitaire")
public class TestSanitaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_test")
    private Integer idTest;

    @ManyToOne
    @JoinColumn(name = "id_travaux")
    private Travaux travaux;

    @Column(name = "date_test")
    private LocalDate dateTest;

    private String resultat; // 'conforme', 'non_conforme'
    private String commentaire;

    public Integer getIdTest() { return idTest; }
    public void setIdTest(Integer idTest) { this.idTest = idTest; }
    public Travaux getTravaux() { return travaux; }
    public void setTravaux(Travaux travaux) { this.travaux = travaux; }
    public LocalDate getDateTest() { return dateTest; }
    public void setDateTest(LocalDate dateTest) { this.dateTest = dateTest; }
    public String getResultat() { return resultat; }
    public void setResultat(String resultat) { this.resultat = resultat; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}
