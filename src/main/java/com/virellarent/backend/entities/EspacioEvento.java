package com.virellarent.backend.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "espacios_evento")
@Data
@NoArgsConstructor
public class EspacioEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;

    @Lob
    private String descripcion;

    @Column(name = "aforo_maximo")
    private int aforoMaximo;

    @OneToMany(mappedBy = "espacio")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "espacio")
    private List<Tarifa> tarifas;
}
