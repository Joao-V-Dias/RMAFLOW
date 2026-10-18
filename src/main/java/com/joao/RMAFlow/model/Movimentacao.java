package com.joao.RMAFlow.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Immutable
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusEquipamento statusAnterior;

    @Enumerated(EnumType.STRING)
    private StatusEquipamento statusNovo;

    @ManyToOne(optional = false)
    private Equipamento equipamento;

    private Movimentacao(Equipamento equipamento ,StatusEquipamento statusAnterior) {
        this.dataHora = LocalDateTime.now();
        this.equipamento = equipamento;
        this.statusAnterior = statusAnterior;
        this.statusNovo = equipamento.getStatus();
    }

    protected Movimentacao() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusEquipamento getStatusAnterior() {
        return statusAnterior;
    }

    public StatusEquipamento getStatusNovo() {
        return statusNovo;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public static Movimentacao registrar(Equipamento equipamento, StatusEquipamento anterior){
        return new Movimentacao(equipamento, anterior);
    }
}
