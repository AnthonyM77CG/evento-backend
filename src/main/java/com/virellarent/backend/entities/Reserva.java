package com.virellarent.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.virellarent.backend.util.EstadoReserva;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    private Integer asistentes;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"reservas", "rol"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_espacio")
    @JsonIgnoreProperties({"reservas", "tarifas"})
    private EspacioEvento espacio;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    @JsonIgnoreProperties("reservas")
    private Plan plan;

    @OneToOne(mappedBy = "reserva")
    @JsonIgnore
    private Pago pago;
}
