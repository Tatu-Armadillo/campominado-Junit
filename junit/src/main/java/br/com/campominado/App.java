package br.com.campominado;

import br.com.campominado.model.Tabuleiro;
import br.com.campominado.view.TabuleiroConsole;

public class App {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
        new TabuleiroConsole(tabuleiro);
    }
}
