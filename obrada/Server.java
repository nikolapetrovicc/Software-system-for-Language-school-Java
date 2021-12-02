/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Server extends Thread{
    private final List<KlijentObrada> konektovaniKlijenti;
    private final ServerSocket soket;
    private int port;

    public Server() throws IOException {
        this.konektovaniKlijenti = new ArrayList();
        this.soket = new ServerSocket(9000);
    }

    @Override
    public void run() {
        while (soket.isClosed() == false) {
            try {
                Socket s = soket.accept();
                KlijentObrada klijent = new KlijentObrada(s);
                konektovaniKlijenti.add(klijent);
                klijent.start();
                
                System.out.println("Klijent je pristupio serveru");
            } catch (SocketException ex) {
                System.out.println("Klijent se iskljucio");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        for (KlijentObrada klijent : konektovaniKlijenti) {
            try {
                klijent.getSocket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ServerSocket getSoket() {
        return this.soket;
    }

    public void Zaustavi() throws IOException {
        soket.close();
    }
}
