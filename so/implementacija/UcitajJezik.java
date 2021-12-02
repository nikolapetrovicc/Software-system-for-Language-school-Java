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

/**
 *
 * @author PC
 */
public class UcitajJezik extends OpstaSistemskaOperacija {

    public UcitajJezik(IDomenski odo) {
        this.odo = odo;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Jezik());
        Jezik jezik = (Jezik) odo;
        List<Jezik> pomocna = lista.stream().map(Jezik.class::cast).filter(e -> e.getSifraJezika().equals(jezik.getSifraJezika())).collect(Collectors.toList());
        List<IDomenski> lista2 = bbp.vrati(new PredavacJezik());

        pomocna.get(0).setLista(new ArrayList());
        pomocna.get(0).getLista().addAll(lista2.stream().map(PredavacJezik.class::cast).filter(e -> e.getJezik().getSifraJezika().equals(pomocna.get(0).getSifraJezika())).collect(Collectors.toList()));

        odo = pomocna.get(0);
    }
}
