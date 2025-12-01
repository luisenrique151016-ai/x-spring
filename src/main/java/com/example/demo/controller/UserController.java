package com.example.demo.controller;

import com.example.app.service.UserService;
import com.example.app.web.dto.UserCreateDTO;
import com.example.app.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserDTO> all(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<UserDTO> porNombre(@PathVariable String nombre) {
        return userService.findByNombre(nombre);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserCreateDTO dto) {
        return userService.create(dto);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Integer id, @RequestBody UserCreateDTO dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}