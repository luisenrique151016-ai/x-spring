package com.example.demo.dto;

import com.example.app.domain.Role;
import com.example.app.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private List<Role> roles;

    public UserDTO(User user, List<Role> roles) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.aPaterno = user.getAPaterno();
        this.aMaterno = user.getAMaterno();
        this.roles = roles;
    }
}