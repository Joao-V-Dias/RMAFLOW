package com.joao.RMAFlow.model;

import com.joao.RMAFlow.exception.StatusInvalidoException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Equipamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String mac;

    private String caixa;

    @Enumerated(EnumType.STRING)
    private StatusEquipamento status;

    private int ciclos;

    @ManyToOne(optional = false)
    private Modelo modelo;

    public Equipamento(String mac, Modelo modelo, String caixa) {
        this.caixa = caixa;
        this.mac = mac;
        this.modelo = modelo;
        this.ciclos = 1;
        registrarEntrada();
    }

    protected Equipamento() {
    }

    public Long getId() {
        return id;
    }

    public String getMac() {
        return mac;
    }

    public String getCaixa() {
        return caixa;
    }

    public StatusEquipamento getStatus() {
        return status;
    }

    public int getCiclos() {
        return ciclos;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void registrarEntrada(){
        if(modelo.isObsoleto()){
            status = StatusEquipamento.OBSOLETO;
        }else{
            status = StatusEquipamento.EM_ANALISE;
        }
    }

    public void registrarNovaEntrada(String caixa){
        validarStatus(StatusEquipamento.EM_USO);
        ciclos+=1;
        this.caixa = caixa;
        registrarEntrada();
    }

    public void aprovar() {
        trocarStatus(StatusEquipamento.EM_ANALISE, StatusEquipamento.DISPONIVEL);
    }

    public void reprovar() {
        trocarStatus(StatusEquipamento.EM_ANALISE, StatusEquipamento.DEFEITO);
    }

    public void marcarEmUso(){
        trocarStatus(StatusEquipamento.DISPONIVEL, StatusEquipamento.EM_USO);
    }

    private void trocarStatus(StatusEquipamento esperado, StatusEquipamento novo) {
        validarStatus(esperado);
        status = novo;
    }

    private void validarStatus(StatusEquipamento esperado){
        if (status != esperado) {
            throw new StatusInvalidoException("Equipamento nao esta no status esperado. Status atual: " + status + ", esperado: " + esperado);
        }
    }
}
