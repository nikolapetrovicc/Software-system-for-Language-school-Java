/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import konceptualne_klase.Jezik;
import konceptualne_klase.Kurs;
import konceptualne_klase.Nivo;

/**
 *
 * @author PC
 */
public class TabelaKurs extends AbstractTableModel{
    private final List<Kurs> elementi;

    private String[] kolone = new String[]{"Opis","Cena","Broj casova", "Datum pocetka", "Jezik", "Nivo"};
    private Class[] tipoviAtributa = new Class[]{ String.class, Double.class, Integer.class, Date.class,Jezik.class,Nivo.class };

    public TabelaKurs(List<Kurs> elementi) {
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
        Kurs kurs = elementi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kurs.getOpisKursa();
            case 1:
                return kurs.getCenaKursa();
            case 2:
                return kurs.getBrojCasova();
            case 3:
                return kurs.getDatumPocetka();
            case 4:
                return kurs.getJezik();
            case 5:
                return kurs.getNivo();
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
