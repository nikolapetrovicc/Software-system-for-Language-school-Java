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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Nivo implements IDomenski, Serializable {

    private Long nivoId;
    private String nazivNivoa;

    public Nivo() {
    }

    public Nivo(Long nivoId, String nazivNivoa) {
        this.nivoId = nivoId;
        this.nazivNivoa = nazivNivoa;
    }

    public Long getNivoId() {
        return nivoId;
    }

    public void setNivoId(Long nivoId) {
        this.nivoId = nivoId;
    }

    public String getNazivNivoa() {
        return nazivNivoa;
    }

    public void setNazivNivoa(String nazivNivoa) {
        this.nazivNivoa = nazivNivoa;
    }

    @Override
    public String toString() {
        return nazivNivoa;
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
        final Nivo other = (Nivo) obj;
        if (!Objects.equals(this.nazivNivoa, other.nazivNivoa)) {
            return false;
        }
        if (!Objects.equals(this.nivoId, other.nivoId)) {
            return false;
        }
        return true;
    }

    @Override
    public String uzmiNazivTabele() {
        return "Nivo";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "Nivo n";
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
        return "WHERE nivoId = " + nivoId;
    }

    @Override
    public List<IDomenski> uzmiEntitete(ResultSet rs) {
        List<IDomenski> elementi = new ArrayList();
        try {
            while (rs.next()) {
                Long id = rs.getLong("nivoId");
                String naziv = rs.getString("nazivNivoa");
                
                Nivo nivo = new Nivo(id, naziv);
                elementi.add(nivo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
