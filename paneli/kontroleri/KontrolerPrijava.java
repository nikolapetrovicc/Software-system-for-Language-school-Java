/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;

import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import konceptualne_klase.Korisnik;
import paneli.JPanelPrijava;
import proxy.Komunikacija;
import storage.Storage;
import transfer.ServerskiOdgovor;
import transfer.Operacija;

/**
 *
 * @author PC
 */
public class KontrolerPrijava {
    private static KontrolerPrijava instanca;
    private JPanelPrijava jpanel;
    private KontrolerPrijava() {
    }

    public static KontrolerPrijava getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerPrijava();
        }
        return instanca;
    }

    public JPanelPrijava getJpanel() {
       
        jpanel = new JPanelPrijava();
        this.jpanel.getjButton1().addActionListener(e -> Prijava());
        this.jpanel.getjBtnKreirajNalog().addActionListener(e -> Kreiraj());
        jpanel.setVisible(true);
        return jpanel;
    }

    private void Prijava() {
        Korisnik k = popuniKorisnika();
        if(k != null){
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(k, Operacija.PrijaviKorisnika);
                if(o.isSignal()){
                    JOptionPane.showMessageDialog(jpanel, "Prijava na sistem uspesna!", "", JOptionPane.INFORMATION_MESSAGE);
                    Storage.getInstance().setKorisnik((Korisnik) o.getObjekat());
                    Window w = SwingUtilities.getWindowAncestor(this.jpanel);
                    w.dispose();
                    if(((Korisnik) o.getObjekat()).isAdministrator()){
                        KontrolerGlavniMeni glavni = new KontrolerGlavniMeni();
                        glavni.getJpanel();
                    }else{
                        KontrolerMeniPolaznik glavni = new KontrolerMeniPolaznik();
                        glavni.getJpanel();
                    }
                            
                    
                }else{
                    JOptionPane.showMessageDialog(jpanel, "Neuspesna prijava na sistem!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerPrijava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void Kreiraj() {
        KontrolerGlavniMeni glavni = new KontrolerGlavniMeni();
        glavni.KreirajNalog();
    }

    private Korisnik popuniKorisnika() {
         try{
            String email = jpanel.getjTxtEmail().getText();
            String sifra = String.copyValueOf(jpanel.getjTxtSifra().getPassword());
            Korisnik k = new Korisnik(new Long(0), "", "", email, sifra, false);
            return k;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(jpanel, "Doslo je do greske prilikom unosa", "", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
