package com.example.gestion_produits_api.exception.domaines;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
