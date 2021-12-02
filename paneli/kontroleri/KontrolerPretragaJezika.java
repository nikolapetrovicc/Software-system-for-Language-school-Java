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
import konceptualne_klase.Jezik;
import paneli.JPanelPretragaJezika;
import proxy.Komunikacija;
import tabele.TabelaJezik;
import transfer.ServerskiOdgovor;
import transfer.Operacija;
import transfer.PretragaUslov;

/**
 *
 * @author PC
 */
public class KontrolerPretragaJezika {
    private static KontrolerPretragaJezika instanca;
    
    private JPanelPretragaJezika jpanel;
    private List<Jezik> lista;
    
    private KontrolerPretragaJezika() {
    }

    public static KontrolerPretragaJezika getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerPretragaJezika();
        }
        return instanca;
    }

    public JPanelPretragaJezika getJpanel() {
       
        jpanel = new JPanelPretragaJezika();
        this.jpanel.getjBtnIzmeni().addActionListener(e -> Izmeni());
        this.jpanel.getjBtnPretraga().addActionListener(e -> Pretraga());
        this.jpanel.getjBtnReset().addActionListener(e -> panelLoad());
        
        panelLoad();
        
        jpanel.setVisible(true);
        return jpanel;
    }

    private void panelLoad() {
        try {
            ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(null, Operacija.VratiJezike);
            if(o.isSignal()){
                lista = (List<Jezik>) o.getObjekat();
                refreshTabele();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(KontrolerPretragaJezika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Izmeni() {
        int jezik = jpanel.getjTable1().getSelectedRow();
        if (jezik == -1) {
            JOptionPane.showMessageDialog(jpanel, "Niste selektovali predavaca za dati jezik", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KontrolerGlavniMeni meni = new KontrolerGlavniMeni();
        meni.KreirajJezik(lista.get(jezik));
        panelLoad();
    }

    private void Pretraga() {
        String lowercaseString = jpanel.getjTxtPretraga().getText().toLowerCase();
        /*jpanel.getjTxtPretraga().getText()*/
        if(lowercaseString != ""){
            try {
                ServerskiOdgovor o = Komunikacija.getInstance().UzmiOdgovor(new PretragaUslov(lowercaseString), Operacija.PretragaJezika);
                if(o.isSignal()){
                    JOptionPane.showMessageDialog(jpanel, "Sistem je uspesno pronasao jezike po zadatom kriterijumu!", "", JOptionPane.INFORMATION_MESSAGE);
                    lista = (List<Jezik>) o.getObjekat();
                    refreshTabele();
                }else{
                    JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da pronadje jezike po zadatom kriterijumu!", "", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                Logger.getLogger(KontrolerKreirajNalog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(jpanel, "Molim Vas unesite neki tekst", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTabele() {
        jpanel.getjTable1().setModel(new TabelaJezik(lista));
    }
}
