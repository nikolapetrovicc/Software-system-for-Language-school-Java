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
import konceptualne_klase.Kurs;
import konceptualne_klase.Nivo;
import konceptualne_klase.Predavac;
import konceptualne_klase.PredavacJezik;

/**
 *
 * @author PC
 */
public class TabelaPredavacJezik extends AbstractTableModel{
    private final List<PredavacJezik> elementi;

    private String[] kolone = new String[]{"Predavac","Napomena"};
    private Class[] tipoviAtributa = new Class[]{Predavac.class ,String.class };

    public TabelaPredavacJezik(List<PredavacJezik> elementi) {
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
        PredavacJezik predavac = elementi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return predavac.getPredavac();
            case 1:
                return predavac.getNapomena();
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
