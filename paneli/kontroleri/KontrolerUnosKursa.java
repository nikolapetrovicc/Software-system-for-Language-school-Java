/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konceptualne_klase.Jezik;
import konceptualne_klase.Kurs;
import konceptualne_klase.Nivo;
import paneli.JPanelUnosKursa;
import proxy.Komunikacija;
import transfer.ServerskiOdgovor;
import transfer.Operacija;

/**
 *
 * @author PC
 */
public class KontrolerUnosKursa {
    private static KontrolerUnosKursa instanca;
    private JPanelUnosKursa jpanel;
    private KontrolerUnosKursa() {
    }

    public static KontrolerUnosKursa getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerUnosKursa();
        }
        return instanca;
    }

    public JPanelUnosKursa getJpanel() {
       
        jpanel = new JPanelUnosKursa();
        this.jpanel.getjBtnKreiraj().addActionListener(e -> Kreiraj());
        
        panelLoad();
        
        jpanel.setVisible(true);
        return jpanel;
    }

    //popunjavamo combo box
    
    private void panelLoad() {
        try {
            ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(null, Operacija.VratiJezike);
            if(o.isSignal()){
                List<Jezik> jezici = (List<Jezik>) o.getObjekat();
                for (Jezik jezik : jezici) {
                    jpanel.getjCmbJezik().addItem(jezik);
                }
            }
            
            o = Komunikacija.getInstance().UzmiOdgovor(null, Operacija.VratiNivoe);
            if(o.isSignal()){
                List<Nivo> nivoi = (List<Nivo>) o.getObjekat();
                for (Nivo nivo : nivoi) {
                    jpanel.getjCmbNivo().addItem(nivo);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(KontrolerUnosKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Kreiraj() {
        Kurs k = popuniKurs();
        if(k != null){
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(k, Operacija.DodajKurs);
                if(o.isSignal()){
                    JOptionPane.showMessageDialog(jpanel, "Sistem je dodao kurs uspesno!", "", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da doda kurs!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerUnosKursa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Kurs popuniKurs() {
        try{
            Jezik jezik = (Jezik) jpanel.getjCmbJezik().getSelectedItem();
            Nivo nivo = (Nivo) jpanel.getjCmbNivo().getSelectedItem();
            if(jpanel.getjTxtOpis().getText().isEmpty() || jpanel.getjTxtCena().getText().isEmpty() || jpanel.getjTxtDatumPocetka().getText().isEmpty() || jpanel.getjTxtBrojCasova().getText().isEmpty()){
                JOptionPane.showMessageDialog(jpanel, "Potrebno je da popunite sva polja!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            String opis = jpanel.getjTxtOpis().getText();
            double cena = Double.parseDouble(jpanel.getjTxtCena().getText());
            if(cena <= 0) {
                JOptionPane.showMessageDialog(jpanel, "Cena mora biti veca od 0!", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            int broj = Integer.parseInt(jpanel.getjTxtBrojCasova().getText());
            if(broj <= 0) {
                JOptionPane.showMessageDialog(jpanel, "Broj casova mora biti veci od 0", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            Date datumPocetka;
            try{
                datumPocetka = new SimpleDateFormat("yyyy-MM-dd").parse(jpanel.getjTxtDatumPocetka().getText());
            }catch(Exception e){
                JOptionPane.showMessageDialog(jpanel, "Pogresno unet datum", "", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            Kurs kurs = new Kurs(new Long(0), opis, cena, broj, datumPocetka, jezik, nivo);
            return kurs;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(jpanel, "Doslo je do greske prilikom unosa", "", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
