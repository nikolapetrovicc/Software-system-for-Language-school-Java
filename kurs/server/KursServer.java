/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurs.server;

import java.awt.Font;
import javax.swing.UIManager;
import paneli.kontroler.KontrolerGlavniMeni;

/**
 *
 * @author PC
 */
public class KursServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            betterGui(new javax.swing.plaf.FontUIResource(new Font("Times New Roman", Font.ITALIC, 14)));
        } catch (Exception e) {
    
        }
         
        KontrolerGlavniMeni.getInstanca().getPanel();
    }
    
    private static void betterGui(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

}
