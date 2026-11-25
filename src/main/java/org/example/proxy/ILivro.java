package org.example.proxy;

import java.util.List;

public interface ILivro {
    List<String> obterDadosLivro();
    String abrirLivro(Usuario usuario);
}
