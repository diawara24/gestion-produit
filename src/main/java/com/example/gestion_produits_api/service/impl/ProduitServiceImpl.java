package com.example.gestion_produits_api.service.impl;

import com.example.gestion_produits_api.dto.ProduitDTO;
import com.example.gestion_produits_api.entity.Categorie;
import com.example.gestion_produits_api.entity.Produit;
import com.example.gestion_produits_api.repository.CategorieRepository;
import com.example.gestion_produits_api.repository.ProduitRepository;
import com.example.gestion_produits_api.service.ProduitService;
import com.example.gestion_produits_api.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    private final CategorieRepository categorieRepository;

    private final Mapper mapper;


    @Override
    public List<ProduitDTO> findAll() {
        return produitRepository.findAll().stream().map(
                produit -> mapper.toDTO(produit, ProduitDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public ProduitDTO findById(Long id) {
        return produitRepository.findById(id).map(
                produit -> mapper.toDTO(produit, ProduitDTO.class)
        ).orElseThrow();
    }

    @Override
    public ProduitDTO save(ProduitDTO dto) {
        Categorie cat = categorieRepository.findById(dto.getCategorieId()).orElseThrow();
        Produit produit = new Produit(null, dto.getNom(), dto.getPrix(), cat);
        return mapper.toDTO(produitRepository.save(produit), ProduitDTO.class);
    }

    @Override
    public ProduitDTO update(Long id, ProduitDTO dto) {
        Produit produit = produitRepository.findById(id).orElseThrow();
        produit.setNom(dto.getNom());
        produit.setPrix(dto.getPrix());
        return mapper.toDTO(produitRepository.save(produit), ProduitDTO.class);
    }

    @Override
    public void delete(Long id) {
        produitRepository.deleteById(id);
    }

}
