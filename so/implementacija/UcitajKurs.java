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
public class UcitajKurs extends OpstaSistemskaOperacija{
    
    public UcitajKurs(IDomenski odo){
        this.odo = odo;
    }
    
    @Override
    protected void izvrsiOperaciju() throws Exception {
        List<IDomenski> lista = bbp.vrati(new Kurs());
        Kurs k = (Kurs) odo;
        
        List<Kurs> pomocna =  lista.stream().map(Kurs.class::cast).filter(e -> e.getSifraKursa().equals(k.getSifraKursa())).collect(Collectors.toList());
        if(pomocna == null || pomocna.size() == 0) throw new Exception("Sistem ne moze da pronadje zadati kurs");
        odo = pomocna.get(0);
    }
}
