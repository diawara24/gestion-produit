package com.example.gestion_produits_api.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Produit {
    @Id @GeneratedValue
    private Long id;
    
    private String nom;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


}
