/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import konceptualne_klase.Jezik;
import paneli.JPanelGlavniMeni;
import paneli.JPanelKreirajNalog;
import paneli.JPanelPretragaJezika;
import paneli.JPanelPretragaKurseva;
import paneli.JPanelPrijava;
import paneli.JPanelUnosJezika;
import paneli.JPanelUnosKursa;
import paneli.JPanelUnosPrijave;

/**
 *
 * @author PC
 */
public class KontrolerGlavniMeni {
    private JPanelGlavniMeni jpanel;

    public void getJpanel() {
        if (jpanel == null) {
            jpanel = new JPanelGlavniMeni();
            this.jpanel.getjMenuItem1().addActionListener(e -> KreirajJezik(null));
            this.jpanel.getjMenuItem2().addActionListener(e -> PretraziJezik());
            this.jpanel.getjMenuItem3().addActionListener(e -> PretraziJezik());
            this.jpanel.getjMenuItem4().addActionListener(e -> KreirajKurs());
            this.jpanel.getjMenuItem5().addActionListener(e -> PretraziKurs());
            this.jpanel.getjMenuItem6().addActionListener(e -> PretraziKurs());
            this.jpanel.getjMenuItem7().addActionListener(e -> PrijavaKurseva());
            jpanel.setSize(400, 300);
            jpanel.setLocationRelativeTo(null);
        }
        jpanel.setVisible(true);
        this.jpanel.getContentPane().removeAll();

    }

    public void dodajPanelNaDialog(JPanel panel) {
        JDialog dialog = new JDialog(new JFrame(), true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void KreirajJezik(Jezik jezik) {
        JPanelUnosJezika panel = KontrolerUnosJezika.getInstanca().getJpanel(jezik);
        dodajPanelNaDialog(panel);
    }
    
    public void Prijava() {
        JPanelPrijava panel = KontrolerPrijava.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }
    
    public void KreirajNalog() {
        JPanelKreirajNalog panel = KontrolerKreirajNalog.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }

    private void PretraziJezik() {
        JPanelPretragaJezika panel = KontrolerPretragaJezika.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }

    private void KreirajKurs() {
        JPanelUnosKursa panel = KontrolerUnosKursa.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }

    private void PretraziKurs() {
        JPanelPretragaKurseva panel = KontrolerPretragaKurseva.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }

    private void PrijavaKurseva() {
        JPanelUnosPrijave panel = KontrolerUnosPrijave.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }

   

}
