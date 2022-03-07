package br.com.swing.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.swing.model.Campo;
import br.com.swing.model.CampoEvento;
import br.com.swing.model.CampoObservador;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

    // #region Colors
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);
    // #endregion

    private Campo campo;

    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_PADRAO);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        campo.registrarObservador(this);
    }

    // #region Aplicar cores nos textos
    private void aplicarEstiloAbrir() {

        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if (campo.isMinado()) {
            setBackground(BG_EXPLODIR);
            return;
        }
        setBackground(BG_PADRAO);

        switch (campo.minasNaVizinhanca()) {
            case 1:
                setForeground(TEXTO_VERDE);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4:
            case 5:
            case 6:
                setForeground(Color.RED);
                break;
            default:
                setForeground(Color.PINK);

        }
        String valor = !campo.vizinhacaSegura() ? campo.minasNaVizinhanca() + "" : "";
        setText(valor);
    }
    // #endregion

    // #region Aplicar cores
    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento) {
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                setForeground(Color.BLACK);
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                setForeground(Color.WHITE);
                break;
            default:
                aplicarEstiloPdrao();
        }

        SwingUtilities.invokeLater(() -> {
            repaint();
            validate();
        });
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_MARCAR);
        setText("M");
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_EXPLODIR);
        setText("X");
    }

    private void aplicarEstiloPdrao() {
        setBorder(BorderFactory.createBevelBorder(0));
        setBackground(BG_PADRAO);
        setText("");
    }
    // #endregion

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            campo.abrir();
        } else {
            campo.alternarMarcacao();
        }
    }

    // #region Metodos da interface não usados
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    // #endregion
}
