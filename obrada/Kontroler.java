/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.util.ArrayList;
import java.util.List;
import konceptualne_klase.Jezik;
import konceptualne_klase.Korisnik;
import konceptualne_klase.Kurs;
import konceptualne_klase.Nivo;
import konceptualne_klase.Predavac;
import konceptualne_klase.Prijava;
import so.genericka.OpstaSistemskaOperacija;
import so.implementacija.DodajJezik;
import so.implementacija.DodajKorisnika;
import so.implementacija.DodajKurs;
import so.implementacija.IzmeniJezik;
import so.implementacija.ObrisiKurs;
import so.implementacija.PretragaJezika;
import so.implementacija.PretragaKurseva;
import so.implementacija.PrijavaKurs;
import so.implementacija.UcitajJezik;
import so.implementacija.UcitajKurs;
import so.implementacija.VratiJezike;
import so.implementacija.VratiKurseve;
import so.implementacija.VratiNivoe;
import so.implementacija.VratiPredavace;
import so.implementacija.PrijavaKorisnika;

import transfer.ServerskiOdgovor;
import static transfer.Operacija.PrijaviKorisnika;
import transfer.PretragaUslov;

/**
 *
 * @author PC
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    ServerskiOdgovor DodajKorisnika(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija oso = new DodajKorisnika(korisnik);
        oso.izvrsi();
        return new ServerskiOdgovor(true, "");
    }

    ServerskiOdgovor PrijaviKorisnika(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija oso = new PrijavaKorisnika(korisnik);
        oso.izvrsi();
        return new ServerskiOdgovor(true, oso.getOdo());
    }

    ServerskiOdgovor VratiPredavace() throws Exception {
        List<Predavac> lista = new ArrayList();
        OpstaSistemskaOperacija oso = new VratiPredavace(new Predavac(), lista);
        oso.izvrsi();
        return new ServerskiOdgovor(true, lista);
    }

    ServerskiOdgovor DodajJezik(Jezik jezik) throws Exception {
        OpstaSistemskaOperacija oso = new DodajJezik(jezik);
        oso.izvrsi();
        return new ServerskiOdgovor(true, "");
    }

    ServerskiOdgovor VratiJezike() throws Exception {
        List<Jezik> lista = new ArrayList();
        OpstaSistemskaOperacija oso = new VratiJezike(new Predavac(), lista);
        oso.izvrsi();
        return new ServerskiOdgovor(true, lista);
    }

    ServerskiOdgovor PretragaJezika(PretragaUslov pretragaUslov) throws Exception {
        List<Jezik> lista = new ArrayList();
        OpstaSistemskaOperacija oso = new PretragaJezika(pretragaUslov, lista);
        oso.izvrsi();
        return new ServerskiOdgovor(true, lista);
    }

    ServerskiOdgovor UcitajJezik(Jezik jezik) throws Exception {
        OpstaSistemskaOperacija oso = new UcitajJezik(jezik);
        oso.izvrsi();
        return new ServerskiOdgovor(true, oso.getOdo());
    }

    ServerskiOdgovor IzmeniJezik(Jezik jezik) throws Exception {
        OpstaSistemskaOperacija oso = new IzmeniJezik(jezik);
        oso.izvrsi();
        return new ServerskiOdgovor(true, "");
    }

    ServerskiOdgovor VratiNivoe() throws Exception {
        List<Nivo> lista = new ArrayList();
        OpstaSistemskaOperacija oso = new VratiNivoe(new Nivo(), lista);
        oso.izvrsi();
        return new ServerskiOdgovor(true, lista);
    }
    
    ServerskiOdgovor DodajKurs(Kurs kurs) throws Exception {
        OpstaSistemskaOperacija oso = new DodajKurs(kurs);
        oso.izvrsi();
        return new ServerskiOdgovor(true, "");
    }

    ServerskiOdgovor VratiKurseve() throws Exception {
        List<Kurs> lista = new ArrayList();
        OpstaSistemskaOperacija oso = new VratiKurseve(new Kurs(), lista);
        oso.izvrsi();
        return new ServerskiOdgovor(true, lista);
    }

    ServerskiOdgovor PretragaKurseva(PretragaUslov pretragaUslov) throws Exception {
        List<Kurs> lista = new ArrayList();
        OpstaSistemskaOperacija oso = new PretragaKurseva(pretragaUslov, lista);
        oso.izvrsi();
        return new ServerskiOdgovor(true, lista);
    }

    ServerskiOdgovor UcitajKurs(Kurs kurs) throws Exception {
        OpstaSistemskaOperacija oso = new UcitajKurs(kurs);
        oso.izvrsi();
        return new ServerskiOdgovor(true, oso.getOdo());
    }

    ServerskiOdgovor ObrisiKurs(Kurs kurs) throws Exception {
        OpstaSistemskaOperacija oso = new ObrisiKurs(kurs);
        oso.izvrsi();
        return new ServerskiOdgovor(true, "");
    }

    ServerskiOdgovor PrijavaKurs(List<Prijava> list) throws Exception {
        OpstaSistemskaOperacija oso = new PrijavaKurs(new Prijava(), list);
        oso.izvrsi();
        return new ServerskiOdgovor(true, "");
    }

}
