package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.dto.CategorieDTO;
import com.example.gestion_produits_api.exception.domaines.AlreadyExistsException;
import com.example.gestion_produits_api.repository.CategorieRepository;
import com.example.gestion_produits_api.service.impl.CategorieServiceImpl;
import com.example.gestion_produits_api.util.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("test")
@SpringBootTest
public class CategorieServiceTest {
    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private Mapper mapper;
    private CategorieServiceImpl categorieService;

    @BeforeEach
    void setUp() {
        categorieService = new CategorieServiceImpl(categorieRepository, mapper);
    }

    @Test
    void shouldCreateAndFetchCategorie() throws Exception {
        CategorieDTO cat = CategorieDTO.builder()
                .nom("Informatique")
                .build();
        CategorieDTO saved = categorieService.create(cat);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Informatique", saved.getNom());

        CategorieDTO found = categorieService.findById(saved.getId());
        Assertions.assertEquals("Informatique", found.getNom());
    }

    @Test
    void shouldThrowExceptionWhenCategorieExist() throws Exception {
        CategorieDTO cat = CategorieDTO.builder()
                .nom("Informatique")
                .build();

        Assertions.assertThrows(AlreadyExistsException.class, () -> categorieService.create(cat));
    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        categorieService.create(new CategorieDTO(null, "Auto"));
        categorieService.create(new CategorieDTO(null, "Maison"));
        List<CategorieDTO> categories = categorieService.findAll();

        Assertions.assertTrue(categories.size() >= 2);
    }
}
