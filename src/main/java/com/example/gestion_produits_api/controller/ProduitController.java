package com.example.gestion_produits_api.controller;

import com.example.gestion_produits_api.dto.ProduitDTO;
import com.example.gestion_produits_api.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public List<ProduitDTO> getAll() {
        return produitService.findAll();
    }

    @PostMapping
    public ProduitDTO create(@RequestBody ProduitDTO dto) {
        return produitService.save(dto);
    }

    @GetMapping("/{id}")
    public ProduitDTO get(@PathVariable Long id) {
        return produitService.findById(id);
    }

    @PutMapping("/{id}")
    public ProduitDTO update(@PathVariable Long id, @RequestBody ProduitDTO dto) {
        return produitService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }
}
