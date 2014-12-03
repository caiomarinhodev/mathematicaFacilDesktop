/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controls;

import com.model.Pergunta;
import com.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio
 */
public class Sistema {
    
    Usuario usuarioAtual;
    List<Pergunta> listaDePerguntas;

    public Sistema() {
    }

    public Sistema(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
        this.listaDePerguntas = new ArrayList<>();
    }

    public Sistema(Usuario usuarioAtual, ArrayList<Pergunta> listaDePerguntas) {
        this.usuarioAtual = usuarioAtual;
        this.listaDePerguntas = listaDePerguntas;
    }
    
    

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public List<Pergunta> getListaDePerguntas() {
        return listaDePerguntas;
    }

    public void setListaDePerguntas(List<Pergunta> listaDePerguntas) {
        this.listaDePerguntas = listaDePerguntas;
    }
    
    
    
    
}
