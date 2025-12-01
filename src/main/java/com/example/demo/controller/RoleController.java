package com.example.demo.controller;

import com.example.app.domain.Role;
import com.example.app.service.RoleService;
import com.example.app.web.dto.RoleCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> all() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role get(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    @PostMapping
    public Role create(@RequestBody RoleCreateDTO dto) {
        return roleService.create(dto);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id, @RequestBody RoleCreateDTO dto) {
        return roleService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        roleService.delete(id);
    }
}