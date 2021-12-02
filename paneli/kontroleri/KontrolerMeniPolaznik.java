/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroleri;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import paneli.JPanelMeniPolaznik;
import paneli.JPanelPretragaKurseva;
import paneli.JPanelUnosPrijave;

/**
 *
 * @author PC
 */
public class KontrolerMeniPolaznik {
    private JPanelMeniPolaznik jpanel;

    public void getJpanel() {
        if (jpanel == null) {
            jpanel = new JPanelMeniPolaznik();
            this.jpanel.getjMenuItem1().addActionListener(e -> PrijavaKurseva());
            this.jpanel.getjMenuItem2().addActionListener(e -> PretraziKurs());
           
            jpanel.setSize(300, 200);
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

   
    private void PretraziKurs() {
        JPanelPretragaKurseva panel = KontrolerPretragaKurseva.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }

    private void PrijavaKurseva() {
        JPanelUnosPrijave panel = KontrolerUnosPrijave.getInstanca().getJpanel();
        dodajPanelNaDialog(panel);
    }
}
