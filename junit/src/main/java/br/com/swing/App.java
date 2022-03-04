package br.com.swing;

import br.com.swing.model.Tabuleiro;

public class App {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
        tabuleiro.registrarObservador(e -> {
            if (e) {
                System.out.println("Ganhou");
            } else {
                System.out.println("Perdeu");
            }
        });
        tabuleiro.alternarMarcacao(0, 0);
        tabuleiro.alternarMarcacao(0, 1);
        tabuleiro.alternarMarcacao(0, 2);
        tabuleiro.alternarMarcacao(1, 0);
        tabuleiro.alternarMarcacao(1, 1);
        tabuleiro.alternarMarcacao(1, 2);
        tabuleiro.alternarMarcacao(2, 0);
        tabuleiro.alternarMarcacao(2, 1);
        tabuleiro.abrir(2, 2);
    }
}
