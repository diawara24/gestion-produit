package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.dto.RegisterUserDTO;
import com.example.gestion_produits_api.dto.UserDTO;
import com.example.gestion_produits_api.entity.User;
import com.example.gestion_produits_api.enums.Role;
import com.example.gestion_produits_api.exception.domaines.AlreadyExistsException;
import com.example.gestion_produits_api.exception.domaines.NotFoundException;
import com.example.gestion_produits_api.repository.UserRepository;
import com.example.gestion_produits_api.service.impl.UserServiceImpl;
import com.example.gestion_produits_api.util.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Profile("test")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(userRepository, passwordEncoder, mapper);
    }

    @Test
    void shouldCreateAndFetchUser() throws Exception {
        RegisterUserDTO dto = new RegisterUserDTO("test", Role.LECTEUR);
        UserDTO saved = service.register(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("test", saved.getUsername());

        User found = service.findUserByUsername(saved.getUsername());
        Assertions.assertEquals("test", found.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenUsernameAlreadyUsed() throws NotFoundException {
        UserDTO user = service.findAll().get(0);
        user.setUsername("mly");
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            service.updateUser(user, user.getId());
        });
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound()  {
        UserDTO user = service.findAll().get(0);
        user.setUsername("mfaty");
        Assertions.assertThrows(NotFoundException.class, () -> {
            service.updateUser(user, 1000L);
        });
    }

    @Test
    void shouldUpdateUser() throws NotFoundException {
        UserDTO user = service.findAll().get(0);
        user.setUsername("mfaty001");
        user.setRole(Role.SUPER_ADMIN);

        UserDTO saved = service.updateUser(user, user.getId());

        Assertions.assertNotNull(saved);
        Assertions.assertEquals("mfaty001", saved.getUsername());
        Assertions.assertEquals(Role.SUPER_ADMIN.name(), saved.getRole().name());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        RegisterUserDTO dto = new RegisterUserDTO("test", Role.LECTEUR);
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            service.register(dto);
        });
    }
}
