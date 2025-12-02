package br.upe.ui;

import br.upe.controller.TarefaControlador;
import br.upe.model.Tarefa;
import br.upe.model.TarefaTableModel;
import br.upe.model.CorRenderer;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

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
        tblTarefas.setDefaultRenderer(Object.class, new CorRenderer());
        tblTarefas.getColumnModel().getColumn(0).setMaxWidth(20);
    }

}
