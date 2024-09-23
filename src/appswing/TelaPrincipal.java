package appswing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import daojpa.Util;
import regras_negocio.Fachada;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        setTitle("Tela Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // Ajuste o número de linhas

        // Botão para abrir a Tela de Vídeos
        JButton videoButton = new JButton("Gerenciar Vídeos");
        videoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        TelaVideo videoFrame = new TelaVideo();
                        videoFrame.setVisible(true);
                    }
                });
            }
        });
        panel.add(videoButton);

        // Botão para abrir a Tela de Gêneros
        JButton generoButton = new JButton("Gerenciar Gêneros");
        generoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        TelaGenero generoFrame = new TelaGenero();
                        generoFrame.setVisible(true);
                    }
                });
            }
        });
        panel.add(generoButton);

        // Botão para abrir a Tela de Consultas
        JButton consultaButton = new JButton("Realizar Consultas");
        consultaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        TelaConsulta consultaFrame = new TelaConsulta();
                        consultaFrame.setVisible(true);
                    }
                });
            }
        });
        panel.add(consultaButton);

        // Botão para sair
        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Util.fecharBanco(); // Método para fechar o banco de dados
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    System.exit(0);
                }
            }
        });
        panel.add(sairButton);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Fachada.inicializar();
                TelaPrincipal frame = new TelaPrincipal();
                frame.setVisible(true);
            }
        });
    }
}