package com.NewDestiny.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String shortDescription;
    private String cancellationPolicy;
    private String houseRules;
    private String healthSafety;
    private String rating;
    private String address;


    @ManyToOne
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "cities_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "features_id", nullable = false)
    private Feature feature;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Schedule> schedules = new ArrayList<>();

}
