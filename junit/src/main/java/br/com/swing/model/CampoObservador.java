package br.com.swing.model;

@FunctionalInterface
public interface CampoObservador {
    public void eventoOcoorreu(Campo campo, CampoEvento evento);
}
