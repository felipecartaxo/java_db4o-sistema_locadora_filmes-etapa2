package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Genero;
import regras_negocio.Fachada;

public class TelaGenero extends JFrame {
    private JTextField nomeField;
    private JTable generoTable;
    private DefaultTableModel tableModel;

    public TelaGenero() {
    	setResizable(false);
        setTitle("Gerenciamento de Gêneros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Remove os botões de minimizar, maximizar e fechar
        setUndecorated(true);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Modelo da tabela
        tableModel = new DefaultTableModel(new String[]{"Nome"}, 0);
        generoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(generoTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4)); // Aumente para 4 para incluir o botão de voltar

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarGeneros();
            }
        });
        buttonPanel.add(listarButton);

        JButton criarButton = new JButton("Criar");
        criarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarGenero();
            }
        });
        buttonPanel.add(criarButton);

        JButton deletarButton = new JButton("Deletar");
        deletarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarGenero();
            }
        });
        buttonPanel.add(deletarButton);

        // Botão de Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de gerenciamento de gêneros
                new TelaPrincipal().setVisible(true); // Abre a tela principal
            }
        });
        buttonPanel.add(voltarButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);

        // Listar os gêneros automaticamente ao iniciar a tela
        listarGeneros();
    }

    private void listarGeneros() {
        try {
            List<Genero> generos = Fachada.listarGeneros();
            tableModel.setRowCount(0);
            for (Genero genero : generos) {
                tableModel.addRow(new Object[]{genero.getNome()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarGenero() {
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        nomeField = new JTextField();

        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Criar Gênero", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                Fachada.criarGenero(nome);
                listarGeneros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deletarGenero() {
        int selectedRow = generoTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Deletar gênero selecionado na tabela
            String nome = (String) tableModel.getValueAt(selectedRow, 0);
            try {
                Fachada.excluirGenero(nome);
                listarGeneros();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Deletar gênero pelo nome digitado
            String nome = JOptionPane.showInputDialog(this, "Digite o nome do gênero a ser deletado:");
            if (nome != null && !nome.isEmpty()) {
                try {
                    Fachada.excluirGenero(nome);
                    listarGeneros();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Fachada.inicializar();
                TelaGenero frame = new TelaGenero();
                frame.setVisible(true);
            }
        });
    }
}