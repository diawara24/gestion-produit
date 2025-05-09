package com.example.gestion_produits_api.service.impl;

import com.example.gestion_produits_api.dto.CategorieDTO;
import com.example.gestion_produits_api.entity.Categorie;
import com.example.gestion_produits_api.exception.domaines.AlreadyExistsException;
import com.example.gestion_produits_api.exception.domaines.NotFoundException;
import com.example.gestion_produits_api.repository.CategorieRepository;
import com.example.gestion_produits_api.service.CategorieService;
import com.example.gestion_produits_api.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    private final Mapper mapper;

    @Override
    public CategorieDTO create(CategorieDTO newCat) throws Exception {
        Categorie exist = findByName(newCat.getNom());
        if (exist != null) {
            throw new AlreadyExistsException("Catégorie existe déjà.");
        }
        Categorie categorie = mapper.toEntity(newCat, Categorie.class);
        return mapper.toDTO(categorieRepository.save(categorie), CategorieDTO.class);
    }

    @Override
    public CategorieDTO update(CategorieDTO updateCat, Long cat_id) throws Exception {
        Categorie exist = findByName(updateCat.getNom());
        if (exist != null && !exist.getId().equals(cat_id)) {
            throw new AlreadyExistsException("Catégorie existe déjà.");
        }
        Categorie categorie = mapper.toEntity(updateCat, Categorie.class);
        categorie.setId(cat_id);
        return mapper.toDTO(categorieRepository.save(categorie), CategorieDTO.class);
    }

    @Override
    public CategorieDTO findById(Long cat_id) throws Exception {
        Categorie cat = categorieRepository.findById(cat_id).orElseThrow(
                () -> new NotFoundException("Catégorie non trouvée")
        );

        return mapper.toDTO(cat, CategorieDTO.class);
    }

    @Override
    public List<CategorieDTO> findAll() {
        return categorieRepository.findAll().stream()
                .map(cat -> mapper.toDTO(cat, CategorieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        categorieRepository.deleteById(id);
    }

    private Categorie findByName(String name) {
        return categorieRepository.findByNom(name);
    }
}
