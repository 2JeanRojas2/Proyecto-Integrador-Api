package com.NewDestiny.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String city;
    private boolean enabled = false;

    @ManyToOne
    @JoinColumn(name = "roles_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    @JsonIncludeProperties(value = {"id"})
    private List<Schedule> schedules = new ArrayList<>();

}
