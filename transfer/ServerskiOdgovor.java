/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class ServerskiOdgovor implements Serializable{
    private boolean signal;
    private Object objekat;

    public ServerskiOdgovor(boolean signal, Object objekat) {
        this.signal = signal;
        this.objekat = objekat;
    }

    public boolean isSignal() {
        return signal;
    }


    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }
}
