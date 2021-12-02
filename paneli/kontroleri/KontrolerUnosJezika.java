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
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import konceptualne_klase.Jezik;
import konceptualne_klase.Predavac;
import konceptualne_klase.PredavacJezik;
import paneli.JPanelUnosJezika;
import proxy.Komunikacija;
import tabele.TabelaPredavacJezik;
import transfer.ServerskiOdgovor;
import transfer.Operacija;

/**
 *
 * @author PC
 */
public class KontrolerUnosJezika {
    private static KontrolerUnosJezika instanca;
    private JPanelUnosJezika jpanel;
    private List<PredavacJezik> lista;
    private Jezik stariJezik;
    private KontrolerUnosJezika() {
    }

    public static KontrolerUnosJezika getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerUnosJezika();
        }
        return instanca;
    }

    public JPanelUnosJezika getJpanel(Jezik stariJezik) {
       
        jpanel = new JPanelUnosJezika();
        this.jpanel.getjBtnDodaj().addActionListener(e -> Dodaj());
        this.jpanel.getjBtnKreiraj().addActionListener(e -> ProveraIzmena());
        this.jpanel.getjBtnObrisi().addActionListener(e -> Obrisi());
        
        panelLoad();
        
        if(stariJezik == null){
            jpanel.getjBtnKreiraj().setText("Kreiraj");
        }else{
            jpanel.getjBtnKreiraj().setText("Izmeni");
            this.stariJezik = stariJezik;
            this.lista = stariJezik.getLista();
            refreshTabele();
            jpanel.getjTxtNaziv().setText(stariJezik.getNazivJezika());
            jpanel.getjTxtNapomena().setText(stariJezik.getNapomena());
        }
        
        jpanel.setVisible(true);
        return jpanel;
    }

    private void Dodaj() {
        PredavacJezik p = popuniPj();
        if(p != null){
            
            lista.add(p);
            refreshTabele();
        }
    }

    private void Kreiraj() {
        Jezik j = popuniJezik();
        //provera za praznu listu
        if(j != null){
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(j, Operacija.DodajJezik);
                if(o.isSignal()){
                    JOptionPane.showMessageDialog(jpanel, "Uspesno ste dodali nov jezik!", "", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da doda jezik!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerUnosPrijave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Obrisi() {
        int pj = jpanel.getjTable1().getSelectedRow();
        if (pj == -1) {
            JOptionPane.showMessageDialog(jpanel, "Niste selektovali predavaca za dati jezik", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        lista.remove(pj);
        refreshTabele();
    }

    private void panelLoad() {
        try {
            ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(null, Operacija.VratiPredavace);
            if(o.isSignal()){
                List<Predavac> predavaci = (List<Predavac>) o.getObjekat();
                for (Predavac predavac : predavaci) {
                    jpanel.getjCmbProfesor().addItem(predavac);
                }
            }
            lista = new ArrayList();
            refreshTabele();
            
        } catch (Exception ex) {
            Logger.getLogger(KontrolerUnosJezika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshTabele() {
        jpanel.getjTable1().setModel(new TabelaPredavacJezik(lista));
    }

    private PredavacJezik popuniPj() {
        try{
            String dodatno = jpanel.getjTxtDodatno().getText();
            Predavac predavac = (Predavac) jpanel.getjCmbProfesor().getSelectedItem();
            if(lista.stream().filter(e -> e.getPredavac().getRbrPredavaca().equals(predavac.getRbrPredavaca())).collect(Collectors.toList()).size() > 0) {
                JOptionPane.showMessageDialog(jpanel, "Vec ste uneli tog profesora za dati jezik", "", JOptionPane.ERROR_MESSAGE);
                return null;
            } 
            PredavacJezik pj = new PredavacJezik(null, predavac, dodatno);
            
            return pj;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(jpanel, "Doslo je do greske prilikom unosa", "", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Jezik popuniJezik() {
         try{
            
            String naziv = jpanel.getjTxtNaziv().getText();
            String napomena = jpanel.getjTxtNapomena().getText();
            if(naziv.isEmpty() || napomena.isEmpty()){
                JOptionPane.showMessageDialog(jpanel, "Potrebno je da popunite sva polja!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if(lista.size() == 0) {
                JOptionPane.showMessageDialog(jpanel, "Morate uneti nekog predavaca za dati jezik", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Jezik jezik = new Jezik(new Long(0), naziv, napomena);
            jezik.setLista(lista);
            return jezik;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(jpanel, "Doslo je do greske prilikom unosa", "", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void ProveraIzmena() {
        if(stariJezik == null) {
            Kreiraj();
        }else{
            Izmeni();
        }
    }

    private void Izmeni() {
        Jezik j = popuniJezik();
        j.setSifraJezika(stariJezik.getSifraJezika());
        if(j != null){
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(j, Operacija.IzmeniJezik);
                if(o.isSignal()){
                    JOptionPane.showMessageDialog(jpanel, "Sistem je uspesno izmenio podatke o jeziku!", "", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da izmeni jezik!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerUnosPrijave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
