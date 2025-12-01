package com.example.demo.service;

import com.example.app.domain.Role;
import com.example.app.repository.RoleRepository;
import com.example.app.web.dto.RoleCreateDTO;
import com.example.app.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final AtomicInteger roleIdGen = new AtomicInteger(1);

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    }

    public Role create(RoleCreateDTO dto) {
        Role role = new Role();
        role.setId(roleIdGen.getAndIncrement());
        role.setNombre(dto.getNombre());
        return roleRepository.save(role);
    }

    public Role update(Integer id, RoleCreateDTO dto) {
        Role role = findById(id);
        role.setNombre(dto.getNombre());
        return roleRepository.save(role);
    }

    public void delete(Integer id) {
        if (!roleRepository.existsById(id))
            throw new NotFoundException("Rol no encontrado");
        roleRepository.deleteById(id);
    }
}