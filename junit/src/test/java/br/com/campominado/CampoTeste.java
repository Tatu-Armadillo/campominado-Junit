package br.com.campominado;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.campominado.model.Campo;

public class CampoTeste {

    private Campo campo;

    @BeforeEach
    public void inicializarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    public void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3,2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    
    @Test
    public void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3,4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoDistancia1Cima() {
        Campo vizinho = new Campo(2,3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    
    @Test
    public void testeVizinhoDistancia1Baixo() {
        Campo vizinho = new Campo(4,3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    public void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2,2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    } 

    @Test
    public void testeNaoVizinho() {
        Campo vizinho = new Campo(1,1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    } 

}
