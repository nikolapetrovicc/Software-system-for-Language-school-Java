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
public class Prijava implements IDomenski, Serializable {

    private Korisnik korisnik;
    private Kurs kurs;
    private Date datumPrijave;
    private String opis;
    private boolean grupniCasovi;

    public Prijava() {
    }

    public Prijava(Korisnik korisnik, Kurs kurs, Date datumPrijave, String opis, boolean grupniCasovi) {
        this.korisnik = korisnik;
        this.kurs = kurs;
        this.datumPrijave = datumPrijave;
        this.opis = opis;
        this.grupniCasovi = grupniCasovi;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isGrupniCasovi() {
        return grupniCasovi;
    }

    public void setGrupniCasovi(boolean grupniCasovi) {
        this.grupniCasovi = grupniCasovi;
    }

    @Override
    public String toString() {
        return "Prijava{" + "korisnik=" + korisnik + ", kurs=" + kurs + ", datumPrijave=" + datumPrijave + ", opis=" + opis + ", grupniCasovi=" + grupniCasovi + '}';
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
        final Prijava other = (Prijava) obj;
        if (this.grupniCasovi != other.grupniCasovi) {
            return false;
        }
        if (!Objects.equals(this.opis, other.opis)) {
            return false;
        }
        if (!Objects.equals(this.korisnik, other.korisnik)) {
            return false;
        }
        if (!Objects.equals(this.kurs, other.kurs)) {
            return false;
        }
        if (!Objects.equals(this.datumPrijave, other.datumPrijave)) {
            return false;
        }
        return true;
    }

    @Override
    public String uzmiNazivTabele() {
        return "Prijava";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "Prijava p";
    }

    @Override
    public String uzmiVrednostiUnos() {
        int broj = 0;
        if (grupniCasovi) {
            broj = 1;
        }
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return korisnik.getKorisnikId() + ", " + kurs.getSifraKursa() + ", '" + date.format(datumPrijave) + "', '" + opis + "' , " + broj;
    }

    @Override
    public String uzmiNaziveKolona() {
        return "korisnikId, sifraKursa, datumPrijava, opis, grupniCasovi";
    }

    @Override
    public String uzmiVrednostiIzmena() {
        return "";
    }

    @Override
    public String uzmiJoinUslov() {
        return " join Korisnik k on (k.korisnikId = p.korisnikId) join Kurs ku on (ku.sifraKursa = p.sifraKursa) join Jezik j on (j.sifraJezika = ku.sifraJezika) join Nivo n on (n.nivoId = ku.nivoId)";
    }

    @Override
    public String uzmiWhereUslov() {
        return "WHERE sifraKursa = " + kurs.getSifraKursa();
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

                Long id = rs.getLong("korisnikId");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                Korisnik k = new Korisnik(id, ime, prezime, email, "", false);

                Date datumPrijave = rs.getDate("datumPrijava");
                String opis = rs.getString("opis");
                boolean grupni = rs.getBoolean("grupniCasovi");

                Prijava prijava = new Prijava(k, kurs, datumPrijave, opis, grupni);

                elementi.add(prijava);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jezik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
