/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import java.util.List;
import java.util.stream.Collectors;
import konceptualne_klase.IDomenski;
import konceptualne_klase.Korisnik;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class PrijavaKorisnika extends OpstaSistemskaOperacija {

    public PrijavaKorisnika(IDomenski odo) {
        this.odo = odo;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Korisnik());
        Korisnik k = (Korisnik) odo;
        
        for (IDomenski iDomenski : lista) {
            Korisnik korisnikBaza = (Korisnik) iDomenski;
            if(korisnikBaza.getEmail().equals(k.getEmail()) && korisnikBaza.getSifra().equals(k.getSifra())){
                odo = korisnikBaza;
            }
        }
        
        /*
        List<IDomenski> lista = bbp.vrati(new Korisnik());
        Korisnik k = (Korisnik) odo;
        List<Korisnik> pomocna =  lista.stream().map(Korisnik.class::cast).filter(e -> e.getEmail().equals(k.getEmail()) && e.getSifra().equals(k.getSifra())).collect(Collectors.toList());
        if(pomocna == null || pomocna.size() == 0) throw new Exception("Sistem ne moze da pronadje zadatog korisnika");
        odo = pomocna.get(0);
         */
    }
}
