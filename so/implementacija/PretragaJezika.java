/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import konceptualne_klase.IDomenski;
import konceptualne_klase.Jezik;
import konceptualne_klase.Kurs;
import konceptualne_klase.PredavacJezik;
import so.genericka.OpstaSistemskaOperacija;
import transfer.PretragaUslov;

/**
 *
 * @author PC
 */
public class PretragaJezika extends OpstaSistemskaOperacija {

    public List<Jezik> jezici;

    public PretragaJezika(IDomenski odo, List<Jezik> jezici) {
        this.odo = odo;
        this.jezici = jezici;
    }
    
    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Jezik());
        PretragaUslov pretraga = (PretragaUslov) odo;
        
        for (IDomenski iDomenski : lista) {
            Jezik e = (Jezik) iDomenski;
            if (e.getNazivJezika().toLowerCase().contains(pretraga.getTekst()) || e.getNapomena().toLowerCase().contains(pretraga.getTekst())) {
                jezici.add(e);
            }
        }

        //Sluzi da iz idomenskog objekta uzme celu listu i da izmapira, prebaci u listu jezika
        /*
        List<Jezik> pomocna = lista.stream().map(Jezik.class::cast)
                .filter(e -> e.getNazivJezika().toLowerCase().contains(pretraga.getTekst()) || e.getNapomena().toLowerCase().contains(pretraga.getTekst()))
                .collect(Collectors.toList());
        
        jezici.addAll(pomocna);
         */
        if (jezici == null || jezici.size() == 0) {
            throw new Exception("Sistem ne moze nista pronaci");
        }

        List<IDomenski> lista2 = bbp.vrati(new PredavacJezik());
        for (Jezik jezik : jezici) {
            jezik.setLista(new ArrayList<>());
            for (IDomenski iDomenski : lista2) {
                PredavacJezik e = (PredavacJezik) iDomenski;
                if (e.getJezik().getSifraJezika().equals(jezik.getSifraJezika())) {
                    jezik.getLista().add(e);

                }
            }
            //jezik.setLista(new ArrayList());
            //jezik.getLista().addAll(lista2.stream().map(PredavacJezik.class::cast).filter(e -> e.getJezik().getSifraJezika().equals(jezik.getSifraJezika())).collect(Collectors.toList()));
        }

    }
}
