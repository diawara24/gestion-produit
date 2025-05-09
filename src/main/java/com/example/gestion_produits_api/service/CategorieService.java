package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.dto.CategorieDTO;
import com.example.gestion_produits_api.entity.Categorie;

import java.util.List;

public interface CategorieService {

    CategorieDTO create(CategorieDTO newCat) throws Exception;

    CategorieDTO update(CategorieDTO updateCat, Long cat_id) throws Exception;

    CategorieDTO findById(Long cat_id) throws Exception;

    List<CategorieDTO> findAll();

    void delete(Long id);
}
