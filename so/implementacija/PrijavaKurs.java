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
import konceptualne_klase.Korisnik;
import konceptualne_klase.Kurs;
import konceptualne_klase.Prijava;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class PrijavaKurs extends OpstaSistemskaOperacija{
    
    public List<Prijava> prijave;
    public PrijavaKurs(IDomenski odo,List<Prijava> elementi){
        this.odo = odo;
        this.prijave = elementi;
    }
    
    @Override
    protected void izvrsiOperaciju() throws Exception {
        if(prijave == null || prijave.size() == 0) return;
        List<IDomenski> lista = bbp.vrati(new Prijava());
        for (Prijava prijava : prijave) {
            ProveraPostoji(prijava, lista);
            bbp.dodaj(prijava);
        }
    }
    
    
    private void ProveraPostoji(Prijava prijava,List<IDomenski> lista) throws Exception{
        
        if(lista.stream().map(Prijava.class::cast)
                .filter(e -> e.getKorisnik().getKorisnikId().equals(prijava.getKorisnik().getKorisnikId()) && e.getKurs().getSifraKursa().equals(prijava.getKurs().getSifraKursa()))
                .collect(Collectors.toList()).size() > 0) 
        {
            throw new Exception("Vec ste se prijavili za dati kurs" );
        }
    }
}
