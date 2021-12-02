/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.genericka;

import baza.BrokerBP;
import konceptualne_klase.IDomenski;

/**
 *
 * @author PC
 */
public abstract class OpstaSistemskaOperacija {
    
    protected BrokerBP bbp;
    protected IDomenski odo;

    public OpstaSistemskaOperacija() {
        bbp = new BrokerBP();
    }
    
    protected abstract void izvrsiOperaciju() throws Exception;
    
    public void izvrsi() throws Exception {
        bbp.konekcija();
        try {
            izvrsiOperaciju();
            bbp.commit();
        } catch (Exception ex) {
            bbp.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            bbp.diskonekcija();
        }
    }

    public IDomenski getOdo(){
        return this.odo;
    }
}
