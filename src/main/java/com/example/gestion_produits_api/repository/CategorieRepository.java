package com.example.gestion_produits_api.repository;

import com.example.gestion_produits_api.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Categorie findByNom(String nom);
}
