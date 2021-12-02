/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konceptualne_klase;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author PC
 */
//sluzi kako bi smo kreirali genericke upite nad bazom podataka
public interface IDomenski {
    
    public String uzmiNazivTabele();
    public String uzmiNazivTabeleDodatno();
    public String uzmiVrednostiUnos();
    public String uzmiNaziveKolona();
    public String uzmiVrednostiIzmena();
    public String uzmiJoinUslov();
    public String uzmiWhereUslov();
    public  List<IDomenski> uzmiEntitete(ResultSet rs);
}
