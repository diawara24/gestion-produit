package com.example.gestion_produits_api.service;

import com.example.gestion_produits_api.dto.RegisterUserDTO;
import com.example.gestion_produits_api.dto.UserDTO;
import com.example.gestion_produits_api.entity.User;
import com.example.gestion_produits_api.exception.domaines.NotFoundException;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);

    List<UserDTO> findAll();
    UserDTO findUserById(Long id) throws NotFoundException;
    UserDTO register(RegisterUserDTO registerUserDTO) throws Exception;

    UserDTO updateUser(UserDTO userDTO, Long id) throws NotFoundException;
    void delete(Long userId) throws NotFoundException;



}
