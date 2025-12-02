package br.upe.ui;

import br.upe.controller.TarefaControlador;
import br.upe.model.Tarefa;
import br.upe.model.CorRenderer;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal {
    private JPanel pnlMain;
    private JTextField txtDescricaoTarefa;
    private JButton btnAdicionarTarefa;
    private JPanel pnlAdicionar;
    private JTable tblTarefas;
    private JCheckBox chkExibirFinalizadas;
    private JComboBox comboBox1;


    private List<Tarefa> tarefas;

    private TarefaControlador controlador;

    public TelaPrincipal() {
        super();
        tarefas = new ArrayList<>();
        comboBox1.addItem("Alta");
        comboBox1.addItem("Média");
        comboBox1.addItem("Baixa");

        btnAdicionarTarefa.addActionListener(e -> {
            adicionarTarefa(txtDescricaoTarefa.getText());
            txtDescricaoTarefa.setText("");
        });
        chkExibirFinalizadas.addActionListener(e -> {
            boolean selecionado = ((JCheckBox) e.getSource()).isSelected();
            controlador.exibirFinalizadas(selecionado);
        });


        tblTarefas.getColumnModel().getColumn(2).setCellRenderer(new CorRenderer());

        //Alteração visual
        txtDescricaoTarefa.putClientProperty("JComponent.roundRect", true);
        txtDescricaoTarefa.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnAdicionarTarefa.setFocusPainted(false);
        btnAdicionarTarefa.setBackground(new Color(60, 130, 200));
        btnAdicionarTarefa.setForeground(Color.WHITE);
        btnAdicionarTarefa.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pnlAdicionar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




    }

    private void adicionarTarefa(String texto) {
        String urg = (String) comboBox1.getSelectedItem();

         int urgencia = 3;

         if (urg.equals("Alta")) {
             urgencia = 1;
         }
        else if (urg.equals("Média")) {
             urgencia = 2;
         } else urgencia = 3;


        Tarefa tarefa = new Tarefa(texto, tarefas.size(), urgencia);
        controlador.adicionarTarefaAtiva(tarefa);
        tblTarefas.revalidate();
        tblTarefas.repaint();
    }

    public JPanel getPnlMain() {
        return this.pnlMain;
    }

    private void createUIComponents() {

        controlador = new TarefaControlador();
        tblTarefas = new JTable(controlador.getTarefaTableModel());
        tblTarefas.setRowHeight(28); // Linhas maiores
        tblTarefas.setShowGrid(false); // remove grade feia
        tblTarefas.setIntercellSpacing(new Dimension(0, 8)); // espaçamento
        tblTarefas.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // fonte moderna
        tblTarefas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));


        tblTarefas.setDefaultRenderer(Object.class, new CorRenderer());
        tblTarefas.getColumnModel().getColumn(0).setMaxWidth(20);


    }

}
