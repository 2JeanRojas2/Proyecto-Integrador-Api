package com.NewDestiny.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

   @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserEntity> userEntities = new ArrayList<>();

}
