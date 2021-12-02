/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import java.util.List;
import java.util.stream.Collectors;
import konceptualne_klase.IDomenski;
import konceptualne_klase.Nivo;
import konceptualne_klase.Predavac;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class VratiPredavace extends OpstaSistemskaOperacija{
    public List<Predavac> predavaci;
    public VratiPredavace(IDomenski odo,List<Predavac> elementi){
        this.odo = odo;
        this.predavaci = elementi;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Predavac());
        
        for (IDomenski iDomenski : lista) {
            predavaci.add((Predavac) iDomenski);
        }
        
        //predavaci.addAll(lista.stream().map(Predavac.class::cast).collect(Collectors.toList()));
    }
}
