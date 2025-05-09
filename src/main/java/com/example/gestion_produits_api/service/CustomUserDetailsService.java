package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.entity.User;
import com.example.gestion_produits_api.repository.UserRepository;
import com.example.gestion_produits_api.util.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé !"));
       LOGGER.info("Utilisateur trouvé avec le username : " + username);
       return new UserPrincipal(user);
    }
}
