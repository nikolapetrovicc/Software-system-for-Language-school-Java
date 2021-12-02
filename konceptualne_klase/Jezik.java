/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konceptualne_klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */

public class Jezik implements IDomenski, Serializable {

    private Long sifraJezika;
    private String nazivJezika;
    private String napomena;
    private List<PredavacJezik> lista;

    public Jezik() {
    }

    public Jezik(Long sifraJezika, String nazivJezika, String napomena) {
        this.sifraJezika = sifraJezika;
        this.nazivJezika = nazivJezika;
        this.napomena = napomena;
    }

    public Long getSifraJezika() {
        return sifraJezika;
    }
    
    public void setSifraJezika(Long sifraJezika) {
        this.sifraJezika = sifraJezika;
    }

    public String getNazivJezika() {
        return nazivJezika;
    }

    public void setNazivJezika(String nazivJezika) {
        this.nazivJezika = nazivJezika;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public List<PredavacJezik> getLista() {
        return lista;
    }

    public void setLista(List<PredavacJezik> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return nazivJezika;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jezik other = (Jezik) obj;
        if (!Objects.equals(this.nazivJezika, other.nazivJezika)) {
            return false;
        }
        if (!Objects.equals(this.napomena, other.napomena)) {
            return false;
        }
        if (!Objects.equals(this.sifraJezika, other.sifraJezika)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String uzmiNazivTabele() {
        return "Jezik";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "Jezik j";
    }

    @Override
    public String uzmiVrednostiUnos() {
        return "'" + nazivJezika + "', '" + napomena + "'";
    }

    @Override
    public String uzmiNaziveKolona() {
        return "nazivJezika, napomena";
    }

    @Override
    public String uzmiVrednostiIzmena() {
        return "nazivJezika = '" + nazivJezika + "',napomena =  '" + napomena + "'";
    }

    @Override
    public String uzmiJoinUslov() {
        return "";
    }

    @Override
    public String uzmiWhereUslov() {
        return "WHERE sifraJezika = " + sifraJezika;
    }

    
    @Override
    public List<IDomenski> uzmiEntitete(ResultSet rs) {
        List<IDomenski> elementi = new ArrayList();
        try {
            while (rs.next()) {
                Long id = rs.getLong("sifraJezika");
                String nazivJezika = rs.getString("nazivJezika");
                String napomena = rs.getString("napomena");

                Jezik j = new Jezik(id, nazivJezika, napomena);
                elementi.add(j);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jezik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
