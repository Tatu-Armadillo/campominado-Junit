package br.com.swing.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Campo {

    private final int linha;
    private final int coluna;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();
    private Set<CampoObservador> observadores = new HashSet<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservador(CampoObservador observador) {
        observadores.add(observador);
    }

    private void notificarObservadores(CampoEvento evento) {
        observadores.stream().forEach(o -> o.eventoOcoorreu(this, evento));
    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }
        return false;
    }

    public void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
            if (marcado) {
                notificarObservadores(CampoEvento.MARCAR);
            }
            notificarObservadores(CampoEvento.DESMARCAR);
        }
    }

    public boolean abrir() {
        if (!aberto && !marcado) {
            if (minado) {
                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }

            setAberto(true);

            notificarObservadores(CampoEvento.ABRIR);
            if (vizinhacaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }

        return false;
    }

    public boolean vizinhacaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar() {
        minado = true;
    }

    // criar teste
    public boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    // criar teste
    public long minasNaVizinhanca() {
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    // criar teste
    public void reiniciar() {
        aberto = false;
        minado = false;
        marcado = false;
    }

    // criar teste

    // #region Getters and Setters

    public boolean isMinado() {
        return minado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
        if (aberto) {
            notificarObservadores(CampoEvento.ABRIR);
        }
    }

    public boolean isFechado() {
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    // #endregion

}
