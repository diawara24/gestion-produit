package com.example.gestion_produits_api.repository;

import com.example.gestion_produits_api.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
