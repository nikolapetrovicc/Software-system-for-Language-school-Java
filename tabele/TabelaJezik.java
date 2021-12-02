/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import konceptualne_klase.Jezik;

/**
 *
 * @author PC
 */
public class TabelaJezik extends AbstractTableModel{
    private final List<Jezik> elementi;

    private String[] kolone = new String[]{"Naziv jezika","Napomena"};
    private Class[] tipoviAtributa = new Class[]{ String.class, String.class };

    public TabelaJezik(List<Jezik> elementi) {
        this.elementi = elementi;
    }

    @Override
    public int getRowCount() {
        if (elementi == null) {
            return 0;
        }
        return elementi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jezik jezik = elementi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return jezik.getNazivJezika();
            case 1:
                return jezik.getNapomena();
            default:
                return "n/a";
        }
    }
   
    @Override
     public String getColumnName(int column) {
        if (column > kolone.length) {
            return "n/a";
        }
        return kolone[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return tipoviAtributa[column];
    } 
}
