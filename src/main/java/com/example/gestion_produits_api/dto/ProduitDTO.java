package com.example.gestion_produits_api.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProduitDTO {
    private Long id;
    private String nom;
    private Double prix;
    private Long categorieId;
}