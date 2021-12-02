/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import konceptualne_klase.Korisnik;
import konceptualne_klase.Kurs;
import konceptualne_klase.Prijava;
import org.graalvm.compiler.nodes.BreakpointNode;
import paneli.JPanelUnosPrijave;
import proxy.Komunikacija;
import storage.Storage;
import tabele.TabelaPrijava;
import transfer.ServerskiOdgovor;
import transfer.Operacija;

/**
 *
 * @author PC
 */
public class KontrolerUnosPrijave {

    private static KontrolerUnosPrijave instanca;
    private JPanelUnosPrijave jpanel;
    private List<Prijava> lista;

    private KontrolerUnosPrijave() {
    }

    public static KontrolerUnosPrijave getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerUnosPrijave();
        }
        return instanca;
    }

    public JPanelUnosPrijave getJpanel() {

        jpanel = new JPanelUnosPrijave();
        this.jpanel.getjBtnDodaj().addActionListener(e -> Dodaj());
        this.jpanel.getjBtnKreiraj().addActionListener(e -> Kreiraj());
        this.jpanel.getjBtnObrisi().addActionListener(e -> Obrisi());

        panelLoad();

        jpanel.setVisible(true);
        return jpanel;
    }

    private void panelLoad() {
        try {
            ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(null, Operacija.VratiKurserve);
            if (o.isSignal()) {
                List<Kurs> kursevi = (List<Kurs>) o.getObjekat();
                for (Kurs kurs : kursevi) {
                    jpanel.getjCmbKurs().addItem(kurs);
                }
            }
            lista = new ArrayList();
            refreshTabele();

        } catch (Exception ex) {
            Logger.getLogger(KontrolerUnosPrijave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Dodaj() {
        Prijava p = popuniPrijavu();
        if (p != null) {
            lista.add(p);
            refreshTabele();
        }
    }

    private void refreshTabele() {
        jpanel.getjTable1().setModel(new TabelaPrijava(lista));
    }

    private void Kreiraj() {

        if (lista.size() > 0) {
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(lista, Operacija.PrijavaKurs);
                if (o.isSignal()) {
                    JOptionPane.showMessageDialog(jpanel, "Prijave su uspesno sacuvane!", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da sacuva prijave!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerUnosPrijave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Obrisi() {
        int prijava = jpanel.getjTable1().getSelectedRow();
        if (prijava == -1) {
            JOptionPane.showMessageDialog(jpanel, "Niste selektovali prijavu", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        lista.remove(prijava);
        refreshTabele();
    }

    private Prijava popuniPrijavu() {
        try {
            Korisnik k = Storage.getInstance().getKorisnik();
            Kurs kurs = (Kurs) jpanel.getjCmbKurs().getSelectedItem();

            String opis = jpanel.getjTxtOpis().getText();
            if (opis.isEmpty() || jpanel.getjTxtDatum().getText().isEmpty()) {
                JOptionPane.showMessageDialog(jpanel, "Potrebno je da popunite sva polja!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Date datum;
            try {
                datum = new SimpleDateFormat("yyyy-MM-dd").parse(jpanel.getjTxtDatum().getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(jpanel, "Pogresno unet datum", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            /*
            if(datum.before(new Date())){
                JOptionPane.showMessageDialog(jpanel, "Datum unosa mora biti posle trenutnog datuma.", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            */
            
            
            boolean grupni = jpanel.getjChbGrupni().isSelected();

            if (lista.stream().filter(e -> e.getKurs().getSifraKursa().equals(kurs.getSifraKursa())).collect(Collectors.toList()).size() > 0) {
                JOptionPane.showMessageDialog(jpanel, "Vec ste se prijavili za taj kurs", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Prijava prijava = new Prijava(k, kurs, datum, opis, grupni);
            return prijava;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, "Doslo je do greske prilikom unosa", "", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
