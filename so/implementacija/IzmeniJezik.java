/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import konceptualne_klase.IDomenski;
import konceptualne_klase.Jezik;
import konceptualne_klase.PredavacJezik;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class IzmeniJezik extends OpstaSistemskaOperacija {

    public IzmeniJezik(IDomenski odo) {
        this.odo = odo;
    }
    
    @Override
    protected void izvrsiOperaciju() throws Exception {
        Jezik j = (Jezik) odo;
        PredavacJezik pj = new PredavacJezik();
        pj.setJezik(j);
        bbp.obrisi(pj);
        bbp.izmeni(j);
        if (j.getLista().size() > 0) {
            for (PredavacJezik predavacJezik : j.getLista()) {
                predavacJezik.setJezik(j);
                bbp.dodaj(predavacJezik);
            }
        }

    }
}
