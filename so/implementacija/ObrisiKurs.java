/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.implementacija;

import konceptualne_klase.IDomenski;
import konceptualne_klase.Kurs;
import konceptualne_klase.Prijava;
import so.genericka.OpstaSistemskaOperacija;

/**
 *
 * @author PC
 */
public class ObrisiKurs extends OpstaSistemskaOperacija{
    public ObrisiKurs(IDomenski odo){
        this.odo = odo;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        Kurs k = (Kurs) odo;
        Prijava p = new Prijava();
        p.setKurs(k);
        bbp.obrisi(p);
        bbp.obrisi(k);
    }
}
