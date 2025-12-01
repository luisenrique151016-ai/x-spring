package com.example.demo.domain;

import lombok.Data;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "TUSUARIO")
public class User {
    @Id
    private Integer id;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private List<Integer> roles;
}