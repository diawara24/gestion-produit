package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.dto.ProduitDTO;

import java.util.List;

public interface ProduitService {
    List<ProduitDTO> findAll();
    ProduitDTO findById(Long id);
    ProduitDTO save(ProduitDTO produitDTO);
    ProduitDTO update(Long id, ProduitDTO produitDTO);
    void delete(Long id);

}
