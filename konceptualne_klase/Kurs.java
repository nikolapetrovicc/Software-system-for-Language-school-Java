/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konceptualne_klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class Kurs implements IDomenski, Serializable {

    private Long sifraKursa;
    private String opisKursa;
    private double cenaKursa;
    private int brojCasova;
    private Date datumPocetka;
    private Jezik jezik;
    private Nivo nivo;

    public Kurs() {
    }

    public Kurs(Long sifraKursa, String opisKursa, double cenaKursa, int brojCasova, Date datumPocetka, Jezik jezik, Nivo nivo) {
        this.sifraKursa = sifraKursa;
        this.opisKursa = opisKursa;
        this.cenaKursa = cenaKursa;
        this.brojCasova = brojCasova;
        this.datumPocetka = datumPocetka;
        this.jezik = jezik;
        this.nivo = nivo;
    }

    public Long getSifraKursa() {
        return sifraKursa;
    }

    public void setSifraKursa(Long sifraKursa) {
        this.sifraKursa = sifraKursa;
    }

    public String getOpisKursa() {
        return opisKursa;
    }

    public void setOpisKursa(String opisKursa) {
        this.opisKursa = opisKursa;
    }

    public double getCenaKursa() {
        return cenaKursa;
    }

    public void setCenaKursa(double cenaKursa) {
        this.cenaKursa = cenaKursa;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Jezik getJezik() {
        return jezik;
    }

    public void setJezik(Jezik jezik) {
        this.jezik = jezik;
    }

    public Nivo getNivo() {
        return nivo;
    }

    public void setNivo(Nivo nivo) {
        this.nivo = nivo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return sifraKursa + " - " + jezik.getNazivJezika();
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
        final Kurs other = (Kurs) obj;
        if (Double.doubleToLongBits(this.cenaKursa) != Double.doubleToLongBits(other.cenaKursa)) {
            return false;
        }
        if (this.brojCasova != other.brojCasova) {
            return false;
        }
        if (!Objects.equals(this.opisKursa, other.opisKursa)) {
            return false;
        }
        if (!Objects.equals(this.sifraKursa, other.sifraKursa)) {
            return false;
        }
        if (!Objects.equals(this.datumPocetka, other.datumPocetka)) {
            return false;
        }
        if (!Objects.equals(this.jezik, other.jezik)) {
            return false;
        }
        if (!Objects.equals(this.nivo, other.nivo)) {
            return false;
        }
        return true;
    }

    @Override
    public String uzmiNazivTabele() {
        return "Kurs";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "Kurs k";
    }

    @Override
    public String uzmiVrednostiUnos() {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + opisKursa + "', " + cenaKursa + ", " + brojCasova + ", '" + date.format(datumPocetka) + "', " + jezik.getSifraJezika() + ", " + nivo.getNivoId();
    }

    @Override
    public String uzmiNaziveKolona() {
        return "opisKursa, cenaKursa, brojCasova, datumPocetka, sifraJezika, nivoId";
    }

    @Override
    public String uzmiVrednostiIzmena() {
        return "";
    }

    @Override
    public String uzmiJoinUslov() {
        return "join Jezik j on (j.sifraJezika = k.sifraJezika) join Nivo n on (n.nivoId = k.nivoId)";
    }

    @Override
    public String uzmiWhereUslov() {
        return "WHERE sifraKursa = " + sifraKursa;
    }

    @Override
    public List<IDomenski> uzmiEntitete(ResultSet rs) {
        List<IDomenski> elementi = new ArrayList();
        try {
            while (rs.next()) {
                Long sifraJezika = rs.getLong("sifraJezika");
                String nazivJezika = rs.getString("nazivJezika");
                Jezik j = new Jezik(sifraJezika, nazivJezika, "");

                Long nivoId = rs.getLong("nivoId");
                String naziv = rs.getString("nazivNivoa");
                Nivo nivo = new Nivo(nivoId, naziv);

                Long sifraKursa = rs.getLong("sifraKursa");
                String opisKursa = rs.getString("opisKursa");
                double cena = rs.getDouble("cenaKursa");
                int broj = rs.getInt("brojCasova");
                Date datum = rs.getDate("datumPocetka");

                Kurs kurs = new Kurs(sifraKursa, opisKursa, cena, broj, datum, j, nivo);
                elementi.add(kurs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jezik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
