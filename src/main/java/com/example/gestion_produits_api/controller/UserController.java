package com.example.gestion_produits_api.controller;

import com.example.gestion_produits_api.dto.RegisterUserDTO;
import com.example.gestion_produits_api.dto.UserDTO;
import com.example.gestion_produits_api.exception.domaines.NotFoundException;
import com.example.gestion_produits_api.service.UserService;
import com.example.gestion_produits_api.util.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/user/register")
    public ResponseEntity<UserDTO> create(@RequestBody RegisterUserDTO registerUserDTO) throws Exception {
        UserDTO userDTO = userService.register(registerUserDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable(name = "id") Long id) throws NotFoundException {
       userService.delete(id);
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
