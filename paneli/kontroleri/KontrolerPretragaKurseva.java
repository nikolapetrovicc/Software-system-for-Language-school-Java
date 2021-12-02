/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konceptualne_klase.Kurs;
import paneli.JPanelPretragaKurseva;
import proxy.Komunikacija;
import tabele.TabelaKurs;
import transfer.ServerskiOdgovor;
import transfer.Operacija;
import transfer.PretragaUslov;

/**
 *
 * @author PC
 */
public class KontrolerPretragaKurseva {
    private static KontrolerPretragaKurseva instanca;
    private JPanelPretragaKurseva jpanel;
    private List<Kurs> lista;
    private KontrolerPretragaKurseva() {
    }

    public static KontrolerPretragaKurseva getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerPretragaKurseva();
        }
        return instanca;
    }

    public JPanelPretragaKurseva getJpanel() {
       
        jpanel = new JPanelPretragaKurseva();
        this.jpanel.getjBtnObrisi().addActionListener(e -> Obrisi());
        this.jpanel.getjBtnPretraga().addActionListener(e -> Pretraga());
        this.jpanel.getjBtnReset().addActionListener(e -> panelLoad());
        
        panelLoad();
        
        jpanel.setVisible(true);
        return jpanel;
    }

    private void panelLoad() {
        if(!storage.Storage.getInstance().getKorisnik().isAdministrator()){
            jpanel.getjBtnObrisi().setVisible(false);
        }else{
            jpanel.getjBtnObrisi().setVisible(true);
        }
        
        try {
            ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(null, Operacija.VratiKurserve);
            if(o.isSignal()){
                lista = (List<Kurs>) o.getObjekat();
                refreshTabele();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(KontrolerPretragaJezika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void Pretraga() {
        String lowercaseString = jpanel.getjTxtPretraga().getText().toLowerCase();
        if(lowercaseString != ""){
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(new PretragaUslov(lowercaseString), Operacija.PretragaKurseva);
                if(o.isSignal()){
                    JOptionPane.showMessageDialog(jpanel, "Sistem je uspesno pronasao kurseve po zadatom kriterijumu!", "", JOptionPane.INFORMATION_MESSAGE);
                    lista = (List<Kurs>) o.getObjekat();
                    refreshTabele();
                }else{
                    JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da pronadje kurseve po zadatom kriterijumu", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerPretragaKurseva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(jpanel, "Molim vas unesite neki tekst", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Obrisi() {
        if(!storage.Storage.getInstance().getKorisnik().isAdministrator()){
            jpanel.getjBtnObrisi().setVisible(false);
        }else{
            jpanel.getjBtnObrisi().setVisible(true);
        }
        int kurs = jpanel.getjTable1().getSelectedRow();
        if (kurs == -1) {
            JOptionPane.showMessageDialog(jpanel, "Niste selektovali kurs", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //provera ako je korisnik administrator, ima opciju da obrise kurs
        
        
        KontrolerGlavniMeni meni = new KontrolerGlavniMeni();
        try {
            ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(lista.get(kurs), Operacija.ObrisiKurs);
            if(o.isSignal()){
                JOptionPane.showMessageDialog(jpanel, "Kurs uspesno obrisan!", "", JOptionPane.INFORMATION_MESSAGE);
                lista.remove(kurs);
                refreshTabele();
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da obrise kurs!", "", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception ex) {
            Logger.getLogger(KontrolerPretragaKurseva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshTabele() {
        jpanel.getjTable1().setModel(new TabelaKurs(lista));
    }
}
