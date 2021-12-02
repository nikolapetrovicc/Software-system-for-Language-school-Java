/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import konceptualne_klase.IDomenski;
import konceptualne_klase.Kurs;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class DodajKurs extends OpstaSistemskaOperacija {

    public DodajKurs(IDomenski odo) {
        this.odo = odo;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        bbp.dodaj((Kurs) odo);
    }
}
