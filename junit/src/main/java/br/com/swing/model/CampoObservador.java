package br.com.swing.model;

@FunctionalInterface
public interface CampoObservador {
    public void eventoOcorreu(Campo campo, CampoEvento evento);
}
