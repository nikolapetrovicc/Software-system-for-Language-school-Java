/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import konceptualne_klase.Korisnik;
import paneli.JPanelKreirajNalog;
import proxy.Komunikacija;
import transfer.ServerskiOdgovor;
import transfer.Operacija;

/**
 *
 * @author PC
 */
public class KontrolerKreirajNalog {

    private static KontrolerKreirajNalog instanca;
    private JPanelKreirajNalog jpanel;

    private KontrolerKreirajNalog() {
    }

    public static KontrolerKreirajNalog getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerKreirajNalog();
        }
        return instanca;
    }

    public JPanelKreirajNalog getJpanel() {

        jpanel = new JPanelKreirajNalog();
        this.jpanel.getjButton1().addActionListener(e -> Kreiraj());
        jpanel.setVisible(true);
        return jpanel;
    }

    private void Kreiraj() {
        Korisnik k = popuniKorisnika();
        if (k != null) {
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(k, Operacija.DodajKorisnika);
                if (o.isSignal()) {
                    JOptionPane.showMessageDialog(jpanel, "Nalog je uspesno kreiran!", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(jpanel, "Nalog nije kreiran!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerKreirajNalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Korisnik popuniKorisnika() {
        try {
            String ime = jpanel.getjTxtIme().getText();
            String prezime = jpanel.getjTxtPrezime().getText();
            String email = jpanel.getjTxtEmail().getText();
            String sifra = String.copyValueOf(jpanel.getjTxtSifra().getPassword());

            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(jpanel, "Potrebno je da unesete ispravan email!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || sifra.isEmpty()) {
                JOptionPane.showMessageDialog(jpanel, "Potrebno je da popunite sva polja!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            /*
            if (!email.endsWith("@gmail.com") && !email.endsWith("@outlook.com") && !email.endsWith("@hotmail.com")) {
                JOptionPane.showMessageDialog(jpanel, "Potrebno je da unesete pravi mail!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            */
            if (sifra.length() < 5) {
                JOptionPane.showMessageDialog(jpanel, "Sifra mora imati vise od 5 karaktera!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Korisnik k = new Korisnik(new Long(0), ime, prezime, email, sifra, false);
            return k;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, "Doslo je do greske prilikom unosa", "", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
