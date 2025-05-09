package com.example.gestion_produits_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategorieDTO {
    private Long id;
    private String nom;
}