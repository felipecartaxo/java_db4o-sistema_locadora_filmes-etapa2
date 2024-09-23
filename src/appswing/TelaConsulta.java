package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Genero;
import modelo.Video;
import regras_negocio.Fachada;

public class TelaConsulta extends JFrame {
    private JTable resultadoTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> consultaComboBox;
    private JTextField parametroField;

    public TelaConsulta() {
        setTitle("Consultas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Remove os botões de minimizar, maximizar e fechar
        setUndecorated(true);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Modelo da tabela
        tableModel = new DefaultTableModel(new Object[]{}, 0);
        resultadoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultadoTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de consultas
        JPanel consultaPanel = new JPanel(new GridLayout(2, 2));

        consultaComboBox = new JComboBox<>(new String[]{
                "Videos por Classificação", "Videos por Título", "Videos por Link", "Videos por Gênero", "Gêneros com Mais Videos"
        });
        consultaPanel.add(new JLabel("Consulta:"));
        consultaPanel.add(consultaComboBox);

        parametroField = new JTextField();
        consultaPanel.add(new JLabel("Parâmetro:"));
        consultaPanel.add(parametroField);

        panel.add(consultaPanel, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        JButton consultarButton = new JButton("Consultar");
        consultarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });
        buttonPanel.add(consultarButton);

        // Botão de Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de consulta
                new TelaPrincipal().setVisible(true); // Abre a tela principal
            }
        });
        buttonPanel.add(voltarButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void consultar() {
        String consulta = (String) consultaComboBox.getSelectedItem();
        String parametro = parametroField.getText();

        try {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            switch (consulta) {
                case "Videos por Classificação":
                    int classificacao = Integer.parseInt(parametro);
                    List<Video> videosPorClassificacao = Fachada.videosPorClassificacao(classificacao);
                    tableModel.setColumnIdentifiers(new String[]{"ID", "Título", "Link", "Classificação"});
                    for (Video video : videosPorClassificacao) {
                        tableModel.addRow(new Object[]{video.getId(), video.getTitulo(), video.getLink(), video.getClassificacao()});
                    }
                    break;
                case "Videos por Título":
                    List<Video> videosPorTitulo = Fachada.videosPorTitulo(parametro);
                    tableModel.setColumnIdentifiers(new String[]{"ID", "Título", "Link", "Classificação"});
                    for (Video video : videosPorTitulo) {
                        tableModel.addRow(new Object[]{video.getId(), video.getTitulo(), video.getLink(), video.getClassificacao()});
                    }
                    break;
                case "Videos por Link":
                    List<Video> videosPorLink = Fachada.videosPorLink(parametro);
                    tableModel.setColumnIdentifiers(new String[]{"ID", "Título", "Link", "Classificação"});
                    for (Video video : videosPorLink) {
                        tableModel.addRow(new Object[]{video.getId(), video.getTitulo(), video.getLink(), video.getClassificacao()});
                    }
                    break;
                case "Videos por Gênero":
                    List<Video> videosPorGenero = Fachada.videosPorGenero(parametro);
                    tableModel.setColumnIdentifiers(new String[]{"ID", "Título", "Link", "Classificação"});
                    for (Video video : videosPorGenero) {
                        tableModel.addRow(new Object[]{video.getId(), video.getTitulo(), video.getLink(), video.getClassificacao()});
                    }
                    break;
                case "Gêneros com Mais Videos":
                    int valor = Integer.parseInt(parametro);
                    List<Genero> generosComMaisVideos = Fachada.generosComMaisVideos(valor);
                    tableModel.setColumnIdentifiers(new String[]{"Nome", "Quantidade de Videos"});
                    for (Genero genero : generosComMaisVideos) {
                        tableModel.addRow(new Object[]{genero.getNome(), genero.getVideos().size()});
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Consulta não reconhecida", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Fachada.inicializar();
                TelaConsulta frame = new TelaConsulta();
                frame.setVisible(true);
            }
        });
    }
}