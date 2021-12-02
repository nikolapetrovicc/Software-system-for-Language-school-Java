/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import java.util.List;
import java.util.stream.Collectors;
import konceptualne_klase.IDomenski;
import konceptualne_klase.Jezik;
import konceptualne_klase.Kurs;
import so.genericka.OpstaSistemskaOperacija;
import transfer.PretragaUslov;

/**
 *
 * @author PC
 */
public class PretragaKurseva extends OpstaSistemskaOperacija {

    public List<Kurs> elementi;

    public PretragaKurseva(IDomenski odo, List<Kurs> elementi) {

        this.odo = odo;
        this.elementi = elementi;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Kurs());
        PretragaUslov pretraga = (PretragaUslov) odo;

        for (IDomenski iDomenski : lista) {
            Kurs e = (Kurs) iDomenski;
            if (e.getOpisKursa().toLowerCase().contains(pretraga.getTekst()) || e.getJezik().getNazivJezika().contains(pretraga.getTekst()) || e.getNivo().getNazivNivoa().contains(pretraga.getTekst())) {
                elementi.add(e);
            }
        }

        if (elementi == null || elementi.size() == 0) {
            throw new Exception("Sistem ne moze nista pronaci");
        }

        /*
        List<Kurs> pomocna = lista.stream().map(Kurs.class::cast)
                .filter(e -> e.getOpisKursa().toLowerCase().contains(pretraga.getTekst()) || e.getJezik().getNazivJezika().contains(pretraga.getTekst()) || e.getNivo().getNazivNivoa().contains(pretraga.getTekst()))
                .collect(Collectors.toList());
        if(pomocna == null || pomocna.size() == 0) throw new Exception("Sistem ne moze nista pronaci");
        elementi.addAll(pomocna);

         */
    }
}
