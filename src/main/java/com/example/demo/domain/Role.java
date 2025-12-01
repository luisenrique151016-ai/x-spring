package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "TROLE")
public class Role {
    @Id
    private Integer id;
    private String nombre;
}