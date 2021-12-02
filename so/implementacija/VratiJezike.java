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
import konceptualne_klase.Predavac;
import konceptualne_klase.PredavacJezik;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class VratiJezike extends OpstaSistemskaOperacija{
    public List<Jezik> jezici;
    
    public VratiJezike(IDomenski odo,List<Jezik> elementi){
        this.odo = odo;
        this.jezici = elementi;
    }
    
    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Jezik());
        for (IDomenski iDomenski : lista) {
            jezici.add((Jezik) iDomenski);
        }
        //jezici.addAll(lista.stream().map(Jezik.class::cast).collect(Collectors.toList()));
        
        
        List<IDomenski> lista2 = bbp.vrati(new PredavacJezik());
        for (Jezik jezik : jezici) {
            jezik.setLista(new ArrayList());
            jezik.getLista().addAll(lista2.stream().map(PredavacJezik.class::cast).filter(e -> e.getJezik().getSifraJezika().equals(jezik.getSifraJezika())).collect(Collectors.toList()));
        }
    }
}
