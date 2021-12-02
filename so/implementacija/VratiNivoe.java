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
import konceptualne_klase.Nivo;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class VratiNivoe extends OpstaSistemskaOperacija{
    public List<Nivo> nivoi;
    
    public VratiNivoe(IDomenski odo,List<Nivo> nivoi){
        this.odo = odo;
        this.nivoi = nivoi;
    }
    
    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Nivo());
        
        for (IDomenski iDomenski : lista) {
            nivoi.add((Nivo) iDomenski);
        }
        
        //nivoi.addAll(lista.stream().map(Nivo.class::cast).collect(Collectors.toList()));
    }
}
