/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import java.util.List;
import java.util.stream.Collectors;
import konceptualne_klase.IDomenski;
import konceptualne_klase.Kurs;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class VratiKurseve extends OpstaSistemskaOperacija{
    public List<Kurs> kursevi;
    public VratiKurseve(IDomenski odo,List<Kurs> elementi){
        this.odo = odo;
        this.kursevi = elementi;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Kurs());
        for (IDomenski iDomenski : lista) {
            kursevi.add((Kurs) iDomenski);
        }
        
        //kursevi.addAll(lista.stream().map(Kurs.class::cast).collect(Collectors.toList()));
    }
}
