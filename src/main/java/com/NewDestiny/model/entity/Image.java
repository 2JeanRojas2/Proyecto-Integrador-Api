package com.NewDestiny.model.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "url", columnDefinition = "varchar(2000)")
    private String url;

    @ManyToOne
    @JoinColumn(name = "products_id", nullable=false)
    @JsonIncludeProperties(value = {"id"})
    private Product product;

}
