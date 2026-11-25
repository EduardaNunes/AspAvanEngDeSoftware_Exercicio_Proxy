package org.example.proxy;

import java.util.Arrays;
import java.util.List;

public class Livro implements ILivro {

    private static int totalCarregamentos = 0;

    private Integer codigo;
    private String titulo;
    private String autor;
    private String conteudo;

    public Livro(int codigo) {
        this.codigo = codigo;
        Livro objeto = BD.getLivro(codigo);
        this.titulo = objeto.titulo;
        this.autor = objeto.autor;
        this.conteudo = objeto.conteudo;
        totalCarregamentos++;
    }

    public Livro(Integer codigo, String titulo, String autor, String conteudo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static int getTotalCarregamentos() {
        return totalCarregamentos;
    }

    public static void zerarCarregamentos() {
        totalCarregamentos = 0;
    }

    @Override
    public List<String> obterDadosLivro() {
        return Arrays.asList(this.titulo, this.autor);
    }

    @Override
    public String abrirLivro(Usuario usuario) {
        return this.conteudo;
    }
}
