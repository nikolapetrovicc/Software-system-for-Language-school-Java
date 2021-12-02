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
public class Predavac implements IDomenski, Serializable {

    private Long rbrPredavaca;
    private String imePrezime;
    private String email;
    private String biografija;

    public Predavac() {
    }

    public Predavac(Long rbrPredavaca, String imePrezime, String email, String biografija) {
        this.rbrPredavaca = rbrPredavaca;
        this.imePrezime = imePrezime;
        this.email = email;
        this.biografija = biografija;
    }

    public Long getRbrPredavaca() {
        return rbrPredavaca;
    }

    public void setRbrPredavaca(Long rbrPredavaca) {
        this.rbrPredavaca = rbrPredavaca;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Predavac other = (Predavac) obj;
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.biografija, other.biografija)) {
            return false;
        }
        if (!Objects.equals(this.rbrPredavaca, other.rbrPredavaca)) {
            return false;
        }
        return true;
    }

    @Override
    public String uzmiNazivTabele() {
        return "Predavac";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "Predavac p";
    }

    @Override
    public String uzmiVrednostiUnos() {
        return "";
    }

    @Override
    public String uzmiNaziveKolona() {
        return "";
    }

    @Override
    public String uzmiVrednostiIzmena() {
        return "";
    }

    @Override
    public String uzmiJoinUslov() {
        return "";
    }

    @Override
    public String uzmiWhereUslov() {
        return "WHERE rbrPredavaca = " + rbrPredavaca;
    }

    @Override
    public List<IDomenski> uzmiEntitete(ResultSet rs) {
        List<IDomenski> elementi = new ArrayList();
        try {
            while (rs.next()) {
                Long id = rs.getLong("rbrPredavaca");
                String imePrezime = rs.getString("imePrezime");
                String email = rs.getString("email");
                String biografija = rs.getString("biografija");
                Predavac predavac = new Predavac(id, imePrezime, email, biografija);
                elementi.add(predavac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Predavac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
