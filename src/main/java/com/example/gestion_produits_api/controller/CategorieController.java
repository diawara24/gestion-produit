package com.example.gestion_produits_api.controller;

import com.example.gestion_produits_api.dto.CategorieDTO;
import com.example.gestion_produits_api.service.CategorieService;
import com.example.gestion_produits_api.util.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<CategorieDTO>> getAll() {
        return ResponseEntity.ok().body(categorieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDTO> getById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(categorieService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @Operation(summary = "Créer une catégorie", description = "Création d'une nouvelle catégorie")
    @ApiResponse(responseCode = "200", description = "Catégorie créé")
    public ResponseEntity<CategorieDTO> create(@RequestBody CategorieDTO dto) throws Exception {
        return ResponseEntity.ok().body(categorieService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<CategorieDTO> update(@RequestBody CategorieDTO dto, @PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(categorieService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable("id") Long id) throws Exception {
        categorieService.delete(id);
        return new ResponseEntity<>(
                new HttpResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK, HttpStatus.OK.getReasonPhrase().toUpperCase(),
                        "User supprimé !"
                ),
                HttpStatus.OK
        );
    }


}
