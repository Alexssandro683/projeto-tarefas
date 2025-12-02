package br.upe.model;

import br.upe.model.TarefaTableModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CorRenderer extends DefaultTableCellRenderer{

    public Component getTableCellRendererComponent(
            JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int col){
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);


        if(col == 2){
            TarefaTableModel model = (TarefaTableModel) table.getModel();
            Tarefa tarefa = model.getTarefa(row);

            switch (tarefa.getUrgencia()){
                case 1:
                    c.setBackground(Color.RED);
                    break;
                case 2:
                    c.setBackground(Color.YELLOW);
                    break;
                case 3:
                    c.setBackground(Color.GREEN);
                    break;
                default:
                    c.setBackground(Color.WHITE);

            }

        }
        return c;
    }
}
