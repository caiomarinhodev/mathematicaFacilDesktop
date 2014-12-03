/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

/**
 *
 * @author Caio
 */
public class Conta {
    
    private String login, senha, frasesecreta;

    public Conta() {
    }

    public Conta(String login, String senha, String frasesecreta) {
        this.login = login;
        this.senha = senha;
        this.frasesecreta = frasesecreta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFrasesecreta() {
        return frasesecreta;
    }

    public void setFrasesecreta(String frasesecreta) {
        this.frasesecreta = frasesecreta;
    }
    
    
    
}
