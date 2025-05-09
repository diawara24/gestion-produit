package com.example.gestion_produits_api.dto;

import com.example.gestion_produits_api.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterUserDTO {
    private String username;
    private Role role;
}