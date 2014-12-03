/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio
 */
public class Usuario {
    
    private int id, pontuacaoAtual;
    private List<Pergunta> historico;
    private Conta conta;

    public Usuario() {
    }

    public Usuario(int id, Conta conta) {
        this.id = id;
        this.pontuacaoAtual = 0;
        this.historico = new ArrayList<>();
        this.conta = conta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontuacaoAtual() {
        return pontuacaoAtual;
    }

    public void setPontuacaoAtual(int pontuacaoAtual) {
        this.pontuacaoAtual = pontuacaoAtual;
    }

    public List<Pergunta> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Pergunta> historico) {
        this.historico = historico;
    }

    

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    
    
    
    
}
