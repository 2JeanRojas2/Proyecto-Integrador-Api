package com.Grupo3.Proyecto.Integrador.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="titulo")
    private String titulo;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="urlImagen")
    private String urlImagen;

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", urlImagen='" + urlImagen + '\'' +
                '}';
    }
}
