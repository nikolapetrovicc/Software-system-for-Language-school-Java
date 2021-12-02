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
public class Korisnik implements IDomenski, Serializable {

    private Long korisnikId;
    private String ime;
    private String prezime;
    private String email;
    private String sifra;
    private boolean administrator;

    public Korisnik() {
    }

    public Korisnik(Long korisnikId, String ime, String prezime, String email, String sifra, boolean administrator) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
        this.administrator = administrator;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return email;
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
        final Korisnik other = (Korisnik) obj;
        if (this.administrator != other.administrator) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        if (!Objects.equals(this.korisnikId, other.korisnikId)) {
            return false;
        }
        return true;
    }

    @Override
    public String uzmiNazivTabele() {
        return "Korisnik";
    }

    @Override
    public String uzmiNazivTabeleDodatno() {
        return "Korisnik k";
    }

    @Override
    public String uzmiVrednostiUnos() {
        int broj = 0;
        if (administrator) {
            broj = 1;
        }
        return "'" + ime + "', '" + prezime + "', ' " + email + "', '" + sifra + "', " + broj;
    }

    @Override
    public String uzmiNaziveKolona() {
        return "ime,prezime,email,sifra,administrator";
    }

    @Override
    public String uzmiVrednostiIzmena() {
        int broj = 0;
        if (administrator) {
            broj = 1;
        }
        return "ime = '" + ime + "',prezime = '" + prezime + "',email = '" + email + "',sifra= '" + sifra + "',administrator = " + broj;
    }

    @Override
    public String uzmiJoinUslov() {
        return "";
    }

    @Override
    public String uzmiWhereUslov() {
        return "WHERE korisnikId = " + korisnikId;
    }

    @Override
    public List<IDomenski> uzmiEntitete(ResultSet rs) {
        List<IDomenski> elementi = new ArrayList();
        try {
            while (rs.next()) {
                Long id = rs.getLong("korisnikId");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                String sifra = rs.getString("sifra");
                boolean admin = rs.getBoolean("administrator");

                Korisnik k = new Korisnik(id, ime, prezime, email, sifra, admin);
                elementi.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elementi;
    }

}
