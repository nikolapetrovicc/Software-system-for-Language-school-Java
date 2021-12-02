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
import konceptualne_klase.Kurs;
import konceptualne_klase.Predavac;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class DodajKorisnika extends OpstaSistemskaOperacija {

    public DodajKorisnika(IDomenski odo) {
        this.odo = odo;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        ProveraPostoji((Korisnik) odo);
        bbp.dodaj((Korisnik) odo);
    }

    private void ProveraPostoji(Korisnik k) throws Exception {
        List<IDomenski> lista = bbp.vrati(new Korisnik());
        
        if (lista.size() > 0) {
            for (IDomenski iDomenski : lista) {
                Korisnik e = (Korisnik) iDomenski;
                if (e.getEmail().equals(k.getEmail())) {
                    throw new Exception("Vec postoji korisnik sa datim emailom");
                }
            }
        }

        /*
        if (lista.stream().map(Korisnik.class::cast).filter(e -> e.getEmail().equals(k.getEmail()))
                .collect(Collectors.toList()).size() > 0) {
            throw new Exception("Vec postoji sa datim emailom");
        }
         */
    }

}
