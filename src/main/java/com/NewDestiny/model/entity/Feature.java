package com.NewDestiny.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kitchen")
    private boolean kitchen;
    @Column(name = "freeParking")
    private boolean freeParking;
    @Column(name = "tv")
    private boolean tv;
    @Column(name = "pool")
    private boolean pool;
    @Column(name = "airConditioning")
    private boolean airConditioning;
    @Column(name = "petsAllowed")
    private boolean petsAllowed;
    @Column(name = "wifi")
    private boolean wifi;

    @OneToMany(mappedBy = "feature")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    }