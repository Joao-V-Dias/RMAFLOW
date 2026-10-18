package com.joao.RMAFlow.model;

import com.joao.RMAFlow.exception.StatusInvalidoException;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private ResultadoTeste resultado;

    private String observacao;

    @ManyToOne(optional = false)
    private Equipamento equipamento;

    protected Teste() {
    }

    private Teste(Equipamento equipamento, ResultadoTeste resultado, String observacao) {
        this.dataHora = LocalDateTime.now();
        this.resultado = resultado;
        this.observacao = observacao;
        this.equipamento = equipamento;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public ResultadoTeste getResultado() {
        return resultado;
    }

    public String getObservacao() {
        return observacao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public static Teste aprovado(Equipamento equipamento){
        equipamento.aprovar();
        return new Teste(equipamento, ResultadoTeste.APROVADO, null);
    }

    public static Teste reprovado(Equipamento equipamento, String motivo){
        if (motivo.isEmpty()) throw new StatusInvalidoException("Equipamento reprado sem motivo!");
        equipamento.reprovar();
        return new Teste(equipamento, ResultadoTeste.REPROVADO, motivo);
    }
}

