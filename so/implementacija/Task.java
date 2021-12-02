/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import java.util.ArrayList;
import java.util.stream.Collectors;
import konceptualne_klase.Korisnik;

/**
 *
 * @author Andrija
 */
public class Task {

    public static void main(String[] args) {
        ArrayList<Korisnik> korisnici = new ArrayList<>();

        korisnici.add(new Korisnik(10l, "Nikola", "Petrovic", "1", "nikola123", true));
        korisnici.add(new Korisnik(50l, "Nikola", "Petrovic", "2", "nikola123", true));
        korisnici.add(new Korisnik(15l, "Nikola", "Petrovic", "3", "nikola123", true));
        korisnici.add(new Korisnik(20l, "Nikola", "Petrovic", "4", "nikola123", true));
        korisnici.add(new Korisnik(1l, "Nikola", "Petrovic", "5", "nikola123", true));
        
        
        //List<IDomenski> lista = bbp.vrati(new Nivo());
        //elementi.addAll(lista.stream().map(Nivo.class::cast).collect(Collectors.toList()));
        ArrayList<Korisnik> lista = new ArrayList<>();
        
        lista.addAll(korisnici.stream().map(Korisnik.class::cast).filter(e -> e.getKorisnikId() > 5).collect(Collectors.toList()));

        for (Korisnik korisnik : lista) {
            System.out.println(korisnik);
        }

    }

}
