package com.example.gestion_produits_api.controller;


import com.example.gestion_produits_api.dto.AuthRequest;
import com.example.gestion_produits_api.entity.User;
import com.example.gestion_produits_api.service.UserService;
import com.example.gestion_produits_api.util.JwtUtil;
import com.example.gestion_produits_api.util.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String JWT_TOKEN_HEADER = "Jwt-Token";

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody AuthRequest request) {
        authenticate(request.getUsername(), request.getPassword());
        User loginUser = userService.findUserByUsername(request.getUsername());
        UserPrincipal principal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(principal);
        return ResponseEntity.ok().headers(jwtHeader).body(loginUser);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(UserPrincipal user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER, jwtUtil.generateToken(user));
        return httpHeaders;
    }
}
