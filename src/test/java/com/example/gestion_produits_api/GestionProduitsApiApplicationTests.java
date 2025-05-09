package com.example.gestion_produits_api;

import com.example.gestion_produits_api.entity.User;
import com.example.gestion_produits_api.enums.Role;
import com.example.gestion_produits_api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Profile("test")
@SpringBootTest
class GestionProduitsApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
