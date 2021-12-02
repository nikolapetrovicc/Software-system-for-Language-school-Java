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
import konceptualne_klase.Predavac;
import konceptualne_klase.PredavacJezik;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class DodajJezik extends OpstaSistemskaOperacija {

    public DodajJezik(IDomenski odo) {
        this.odo = odo;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        Jezik j = (Jezik) odo;
        ProveraPostoji(j);
        Long id = bbp.dodaj(j);
        j.setSifraJezika(id);

        if (j.getLista().size() > 0) {
            for (PredavacJezik predavacJezik : j.getLista()) {
                predavacJezik.setJezik(j);
                bbp.dodaj(predavacJezik);
            }
        }
    }
    //MAP - mapira iz konkretnog objekta u opsti domenski objekat

    private void ProveraPostoji(Jezik jezik) throws Exception {
        List<IDomenski> lista = bbp.vrati(new Jezik());

        for (IDomenski iDomenski : lista) {
            Jezik j = (Jezik) iDomenski;
            if(j.getNazivJezika().equals(jezik.getNazivJezika())){
                throw new Exception("Vec postoji jezik sa datim imenom");
            }
        }
        
        /*
        if(lista.stream().map(Jezik.class::cast)
                .filter(e -> e.getNazivJezika().equals(jezik.getNazivJezika()))
                .collect(Collectors.toList()).size() > 0) {
            throw new Exception("Vec postoji jezik sa datim imenom");
        }
        
        */
        
    }

}
