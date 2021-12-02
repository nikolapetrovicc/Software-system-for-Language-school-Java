/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konceptualne_klase.Jezik;
import konceptualne_klase.Korisnik;
import konceptualne_klase.Kurs;
import konceptualne_klase.Prijava;
import transfer.ServerskiOdgovor;
import transfer.Operacija;
import transfer.PretragaUslov;
import transfer.KlijentskiZahtev;

/**
 *
 * @author PC
 */
public class KlijentObrada extends Thread {

    private final Socket soket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    public KlijentObrada(Socket socket) throws IOException {
        this.soket = socket;
        this.input = new ObjectInputStream(socket.getInputStream());
        this.output = new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return soket;
    }

    @Override
    public void run() {
        while (soket.isClosed() == false) {
            try {
                KlijentskiZahtev kz = (KlijentskiZahtev) input.readObject();
                ServerskiOdgovor odgovor = Obradi(kz);
                this.output.writeObject(odgovor);
                this.output.flush();
            } catch (EOFException ex) {
                try {
                    soket.close();
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(KlijentObrada.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException | ClassNotFoundException ex) {
                try {
                    soket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(KlijentObrada.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    private ServerskiOdgovor Obradi(KlijentskiZahtev kz) {
        try {
            switch (kz.getOperacija()) {
                case Operacija.DodajKorisnika:
                    return Kontroler.getInstanca().DodajKorisnika((Korisnik) kz.getObjekat());
                case Operacija.PrijaviKorisnika:
                    return Kontroler.getInstanca().PrijaviKorisnika((Korisnik) kz.getObjekat());
                case Operacija.VratiPredavace:
                    return Kontroler.getInstanca().VratiPredavace();
                case Operacija.DodajJezik:
                    return Kontroler.getInstanca().DodajJezik((Jezik) kz.getObjekat());
                case Operacija.VratiJezike:
                    return Kontroler.getInstanca().VratiJezike();
                case Operacija.PretragaJezika:
                    return Kontroler.getInstanca().PretragaJezika((PretragaUslov) kz.getObjekat());
                case Operacija.UcitajJezik:
                    return Kontroler.getInstanca().UcitajJezik((Jezik) kz.getObjekat());
                case Operacija.IzmeniJezik:
                    return Kontroler.getInstanca().IzmeniJezik((Jezik) kz.getObjekat());
                case Operacija.VratiNivoe:
                    return Kontroler.getInstanca().VratiNivoe();
                case Operacija.DodajKurs:
                    return Kontroler.getInstanca().DodajKurs((Kurs) kz.getObjekat());
                case Operacija.VratiKurserve:
                    return Kontroler.getInstanca().VratiKurseve();
                case Operacija.PretragaKurseva:
                    return Kontroler.getInstanca().PretragaKurseva((PretragaUslov) kz.getObjekat());
                case Operacija.UcitajKurs:
                    return Kontroler.getInstanca().UcitajKurs((Kurs) kz.getObjekat());
                case Operacija.ObrisiKurs:
                    return Kontroler.getInstanca().ObrisiKurs((Kurs) kz.getObjekat());
                case Operacija.PrijavaKurs:
                    return Kontroler.getInstanca().PrijavaKurs((List<Prijava>) kz.getObjekat());
            }
            return new ServerskiOdgovor(false, "");
        } catch (Exception ex) {
            return new ServerskiOdgovor(false, "");
        }
    }
}
