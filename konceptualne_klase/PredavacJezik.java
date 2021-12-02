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
public class PredavacJezik implements IDomenski, Serializable {

    private Jezik jezik;
    private Predavac predavac;
    private String napomena;

    public PredavacJezik() {
    }

    public PredavacJezik(Jezik jezik, Predavac predavac, String napomena) {
        this.jezik = jezik;
        this.predavac = predavac;
        this.napomena = napomena;
    }

    public Jezik getJezik() {
        return jezik;
    }

    public void setJezik(Jezik jezik) {
        this.jezik = jezik;
    }

    public Predavac getPredavac() {
        return predavac;
    }

    public void setPredavac(Predavac predavac) {
        this.predavac = predavac;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "PredavacJezik{" + "jezik=" + jezik + ", predavac=" + predavac + ", napomena=" + napomena + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final PredavacJezik other = (PredavacJezik) obj;
        if (!Objects.equals(this.napomena, other.napomena)) {
            return false;
        }
        if (!Objects.equals(this.jezik, other.jezik)) {
            return false;
        }
        if (!Objects.equals(this.predavac, other.predavac)) {
            return false;
        }
        return true;
    }

    @Override
    public String uzmiNazivTabele() {
        return "PredavacJezik";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "PredavacJezik pj";
    }

    @Override
    public String uzmiVrednostiUnos() {
        return jezik.getSifraJezika() + ", " + predavac.getRbrPredavaca() + ", '" + napomena + "'";
    }

    @Override
    public String uzmiNaziveKolona() {
        return "sifraJezika, rbrPredavaca, napomenaPj";
    }

    @Override
    public String uzmiVrednostiIzmena() {
        return " sifraJezika = " + jezik.getSifraJezika() + ", rbrPredavaca = " + predavac.getRbrPredavaca() + ", napomenaPj = '" + napomena + "'";
    }

    @Override
    public String uzmiJoinUslov() {
        return "join Jezik j on (j.sifraJezika = pj.sifraJezika) join Predavac p on (p.rbrPredavaca = pj.rbrPredavaca)";
    }

    @Override
    public String uzmiWhereUslov() {
        return "WHERE sifraJezika = " + jezik.getSifraJezika();
    }

    @Override
    public List<IDomenski> uzmiEntitete(ResultSet rs) {
        List<IDomenski> elementi = new ArrayList();
        try {
            while (rs.next()) {
                Long jezikId = rs.getLong("sifraJezika");
                String nazivJezika = rs.getString("nazivJezika");
                String napomena = rs.getString("napomena");
                Jezik j = new Jezik(jezikId, nazivJezika, napomena);

                Long id = rs.getLong("rbrPredavaca");
                String imePrezime = rs.getString("imePrezime");
                String email = rs.getString("email");
                String biografija = rs.getString("biografija");
                Predavac predavac = new Predavac(id, imePrezime, email, biografija);

                String napomena2 = rs.getString("napomenaPj");

                PredavacJezik pj = new PredavacJezik(j, predavac, napomena2);
                elementi.add(pj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PredavacJezik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
