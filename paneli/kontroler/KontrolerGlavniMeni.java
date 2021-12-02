/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneli.kontroler;

import java.io.IOException;
import javax.swing.JOptionPane;
import paneli.JPanelGlavniMeni;
import proxy.Komunikacija;

/**
 *
 * @author PC
 */
public class KontrolerGlavniMeni {

    private JPanelGlavniMeni frame;
    private static KontrolerGlavniMeni instanca;

    private KontrolerGlavniMeni() {
    }

    public static KontrolerGlavniMeni getInstanca() {
        if (instanca == null) {
            instanca = new KontrolerGlavniMeni();
        }
        return instanca;
    }

    public JPanelGlavniMeni getPanel() {
        frame = new JPanelGlavniMeni();
        this.frame.getjMenuItem1().addActionListener(e -> Start());
        this.frame.getjMenuItem2().addActionListener(e -> Stop());
        frame.setVisible(true);
        return frame;
    }

    private void Start() {
        try {
            Komunikacija.getInstanca().Start();
            JOptionPane.showMessageDialog(frame, "Uspesno pokrenut server", "", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Neuspesno pokrenut server", "", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void Stop() {
        try {
            Komunikacija.getInstanca().Stop();
            JOptionPane.showMessageDialog(frame, "Uspesno zaustavljen server", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Neuspesno zaustavljen server", "", JOptionPane.INFORMATION_MESSAGE);

        }
    }

}
