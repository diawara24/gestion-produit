package com.example.gestion_produits_api.dto;

import com.example.gestion_produits_api.enums.Role;
import lombok.*;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private Role role;
    private List<String> authorities;
}