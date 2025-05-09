package com.example.gestion_produits_api;

import com.example.gestion_produits_api.entity.User;
import com.example.gestion_produits_api.enums.Role;
import com.example.gestion_produits_api.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("test")
public class TestDataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TestDataInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            User superAdmin = new User(null, "superAdmin", passwordEncoder.encode("Passer@1234"), Role.SUPER_ADMIN, Arrays.stream(Role.SUPER_ADMIN.getAuthorities()).toList());
            User admin = new User(null, "mfaty", passwordEncoder.encode("Passer@1234"), Role.ADMIN, Arrays.stream(Role.ADMIN.getAuthorities()).toList());
            User lecteur = new User(null, "mly", passwordEncoder.encode("Passer@1234"), Role.LECTEUR, Arrays.stream(Role.LECTEUR.getAuthorities()).toList());
            userRepository.saveAll(List.of(admin, lecteur, superAdmin));
        }
    }
}

