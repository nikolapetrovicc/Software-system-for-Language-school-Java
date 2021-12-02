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
public class Operacija implements Serializable{
    public static final int DodajKorisnika = 1;
    public static final int PrijaviKorisnika = 2;
    public static final int VratiPredavace = 3;
    public static final int DodajJezik = 4;
    public static final int VratiJezike = 5;
    public static final int PretragaJezika = 6;
    public static final int UcitajJezik  = 7;
    public static final int IzmeniJezik = 8;
    public static final int VratiNivoe = 9;
    public static final int DodajKurs = 10;
    public static final int VratiKurserve = 11;
    public static final int PretragaKurseva = 12;
    public static final int UcitajKurs = 13;
    public static final int ObrisiKurs = 14;
    public static final int PrijavaKurs = 15;
}
