/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import konceptualne_klase.Predavac;
import konceptualne_klase.PredavacJezik;
import konceptualne_klase.Prijava;

/**
 *
 * @author PC
 */
public class TabelaPrijava extends AbstractTableModel{
    private final List<Prijava> elementi;

    private String[] kolone = new String[]{"Kurs","Datum", "Opis", "Grupni"};
    private Class[] tipoviAtributa = new Class[]{Predavac.class ,Date.class,String.class, boolean.class };

    public TabelaPrijava(List<Prijava> elementi) {
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
        Prijava prijava = elementi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prijava.getKurs();
            case 1:
                return prijava.getDatumPrijave();
            case 2:
                return prijava.getOpis();
            case 3:
                return prijava.isGrupniCasovi();
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
