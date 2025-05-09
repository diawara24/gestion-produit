package com.example.gestion_produits_api.enums;

public enum Role {
    SUPER_ADMIN("create", "read", "update", "delete"),
    ADMIN("create", "read", "update", "delete"),
    COLLABORATEUR("create", "read", "update"),
    LECTEUR("read");

    private final String[] authorities;
    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
