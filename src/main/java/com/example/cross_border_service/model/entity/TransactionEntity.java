package com.example.cross_border_service.model.entity;

import com.example.cross_border_service.model.TipoDeMoeda;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private UserEntity destinatario;

    private TipoDeMoeda moeda;

    private Double saldoAnterior;

    private Double valor;

    private LocalDateTime data;

}