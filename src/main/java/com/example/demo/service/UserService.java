package com.example.demo.service;

import com.example.app.domain.Role;
import com.example.app.domain.User;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import com.example.app.web.dto.UserCreateDTO;
import com.example.app.web.dto.UserDTO;
import com.example.app.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AtomicInteger userIdGen = new AtomicInteger(1);

    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toDTO);
    }

    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return toDTO(user);
    }

    public List<UserDTO> findByNombre(String nombre) {
        return userRepository.findByNombre(nombre).stream()
                .map(this::toDTO).toList();
    }

    public UserDTO create(UserCreateDTO dto) {
        User user = new User();
        user.setId(userIdGen.getAndIncrement());
        user.setNombre(dto.getNombre());
        user.setAPaterno(dto.getAPaterno());
        user.setAMaterno(dto.getAMaterno());
        user.setRoles(dto.getRoles());

        return toDTO(userRepository.save(user));
    }

    public UserDTO update(Integer id, UserCreateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        user.setNombre(dto.getNombre());
        user.setAPaterno(dto.getAPaterno());
        user.setAMaterno(dto.getAMaterno());
        user.setRoles(dto.getRoles());

        return toDTO(userRepository.save(user));
    }

    public void delete(Integer id) {
        if (!userRepository.existsById(id))
            throw new NotFoundException("Usuario no encontrado");

        userRepository.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        List<Role> roles = roleRepository.findAllById(user.getRoles());
        return new UserDTO(user, roles);
    }
}