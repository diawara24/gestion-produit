package com.example.gestion_produits_api.service.impl;

import com.example.gestion_produits_api.dto.RegisterUserDTO;
import com.example.gestion_produits_api.dto.UserDTO;
import com.example.gestion_produits_api.entity.User;;
import com.example.gestion_produits_api.exception.domaines.AlreadyExistsException;
import com.example.gestion_produits_api.exception.domaines.NotFoundException;
import com.example.gestion_produits_api.repository.UserRepository;
import com.example.gestion_produits_api.service.UserService;
import com.example.gestion_produits_api.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final Mapper mapper;
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).
                orElseThrow();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(
                user -> mapper.toDTO(user, UserDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Utilisateur non trouvé !"));
        return mapper.toDTO(user, UserDTO.class);
    }

    @Override
    public UserDTO register(RegisterUserDTO dto) {
        Optional<User> user = userRepository.findByUsername(dto.getUsername());
        if (user.isPresent()) {
            throw new AlreadyExistsException("L'utilisateur " + dto.getUsername() + " existe déjà.");
        }
        User newUser;
        String password = genPassword();
        List<String> authorities = List.of(dto.getRole().getAuthorities());
        newUser = mapper.toEntity(dto, User.class);
        newUser.setAuthorities(authorities);
        newUser.setPassword(password);
        return mapper.toDTO(userRepository.save(newUser), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) throws NotFoundException {
        User user = findById(id);
        if (user != null) {
            if (!user.getUsername().equals(userDTO.getUsername())) {
                Optional<User> exist = userRepository.findByUsername(userDTO.getUsername());
                if (exist.isPresent()) {
                    throw new AlreadyExistsException("Le username" + userDTO.getUsername() + " est déjà utilisé.");
                }
            }
           User updateUser = mapper.toEntity(userDTO, User.class);
           if (!user.getRole().equals(userDTO.getRole())) {
              updateUser.setAuthorities(Arrays.asList(userDTO.getRole().getAuthorities()));
           }
           userRepository.save(updateUser);
           return mapper.toDTO(updateUser, UserDTO.class);
        }
        return null;
    }

    @Override
    public void delete(Long userId) throws NotFoundException {
        User user = findById(userId);
        if (user != null)
            userRepository.deleteById(userId);
    }

    private User findById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Utilisateur non trouvé !")
        );
    }

    private String genPassword() {
        return passwordEncoder.encode("Passer@1234");
    }
}
