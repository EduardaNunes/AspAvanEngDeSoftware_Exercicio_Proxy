package org.example.proxy;

public class Usuario {
    private String nome;
    private boolean assinante;

    public Usuario(String nome, boolean assinante) {
        this.nome = nome;
        this.assinante = assinante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAssinante() {
        return assinante;
    }

    public void setAssinante(boolean assinante) {
        this.assinante = assinante;
    }
}
