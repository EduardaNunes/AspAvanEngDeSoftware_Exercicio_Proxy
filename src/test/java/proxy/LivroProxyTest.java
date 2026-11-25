package proxy;

import org.example.proxy.BD;
import org.example.proxy.Livro;
import org.example.proxy.LivroProxy;
import org.example.proxy.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LivroProxyTest {

    @BeforeEach
    void setUp() {
        BD.addLivro(new Livro(1, "Padrões de Projeto", "Erich Gamma", "Conteúdo PDF do livro Padrões de Projeto"));
        BD.addLivro(new Livro(2, "Clean Code", "Robert Martin", "Conteúdo PDF do livro Clean Code"));
        Livro.zerarCarregamentos();
    }

    @Test
    void deveRetornarDadosLivro() {
        LivroProxy livro = new LivroProxy(1);

        assertEquals(Arrays.asList("Padrões de Projeto", "Erich Gamma"), livro.obterDadosLivro());
    }

    @Test
    void deveAbrirLivro() {
        Usuario usuario = new Usuario("Ana", true);
        LivroProxy livro = new LivroProxy(2);

        assertEquals("Conteúdo PDF do livro Clean Code", livro.abrirLivro(usuario));
    }

    @Test
    void deveRetonarExcecaoUsuarioNaoAutorizadoAbrirLivro() {
        try {
            Usuario usuario = new Usuario("Joana", false);
            LivroProxy livro = new LivroProxy(2);

            livro.abrirLivro(usuario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Usuário não autorizado", e.getMessage());
        }
    }

    @Test
    void naoDeveCarregarLivroAoCriarProxy() {
        new LivroProxy(1);

        assertEquals(0, Livro.getTotalCarregamentos());
    }

    @Test
    void deveCarregarLivroApenasNaPrimeiraConsulta() {
        Usuario usuario = new Usuario("Ana", true);
        LivroProxy livro = new LivroProxy(1);

        livro.obterDadosLivro();
        livro.abrirLivro(usuario);
        livro.obterDadosLivro();

        assertEquals(1, Livro.getTotalCarregamentos());
    }

    @Test
    void naoDeveCarregarLivroQuandoUsuarioNaoAutorizado() {
        try {
            Usuario usuario = new Usuario("Joana", false);
            LivroProxy livro = new LivroProxy(1);

            livro.abrirLivro(usuario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals(0, Livro.getTotalCarregamentos());
        }
    }
}
