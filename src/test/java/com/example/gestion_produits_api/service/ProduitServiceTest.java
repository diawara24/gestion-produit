package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.dto.CategorieDTO;
import com.example.gestion_produits_api.repository.CategorieRepository;
import com.example.gestion_produits_api.repository.ProduitRepository;
import com.example.gestion_produits_api.service.impl.ProduitServiceImpl;
import com.example.gestion_produits_api.util.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootTest
public class ProduitServiceTest {
    @Autowired
    private ProduitRepository produitRepository;

    private CategorieRepository categorieRepository;

    @Autowired
    private Mapper mapper;

    private ProduitServiceImpl produitService;

    @BeforeEach
    void setup() {
        produitService = new ProduitServiceImpl(produitRepository, categorieRepository, mapper);
    }



}
