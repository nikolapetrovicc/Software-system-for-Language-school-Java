/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import konceptualne_klase.IDomenski;

/**
 *
 * @author PC
 */

public class BrokerBP {
    private Connection connection;
    private String sifra;
    private String drajver;
    private String putanja;
    private String korisnicko;

    public BrokerBP() {
       this.postavka();

    }
    
    public void konekcija() throws Exception {
        try {
            connection = DriverManager.getConnection(putanja, korisnicko, sifra);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void postavka() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "config/db.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            this.korisnicko = properties.getProperty("user");
            this.sifra = properties.getProperty("sifra");
            this.putanja = properties.getProperty("putanja");
            this.drajver = properties.getProperty("driver");
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     public void diskonekcija() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                this.connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception();
            }
        }
    }

    
    public List<IDomenski> vrati(IDomenski odo) throws SQLException {
        try {
            Statement stanje = connection.createStatement();
            
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ")
                    .append(odo.uzmiNazivTabeleDodatno())
                    .append(" ")
                    .append(odo.uzmiJoinUslov())
                    .append(";");
            String upit = sb.toString();
            
            ResultSet res = stanje.executeQuery(upit);
            return odo.uzmiEntitete(res);
            //prodjemo kroz te vrednosti
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Long dodaj(IDomenski odo) throws SQLException {
        try {
            Statement stanje = connection.createStatement();
            
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(odo.uzmiNazivTabele())
                    .append(" (").append(odo.uzmiNaziveKolona()).append(")")
                    .append(" VALUES (").append(odo.uzmiVrednostiUnos()).append(")");
            String upit = sb.toString();
            
            stanje.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
            ResultSet res = stanje.getGeneratedKeys();
            if (res.next()) {
                return res.getLong(1);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return -1l;
    }

    

    public int izmeni(IDomenski odo) throws SQLException {
        try {
            Statement stanje = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(odo.uzmiNazivTabele())
                    .append(" SET ")
                    .append(odo.uzmiVrednostiIzmena()).append(" ")
                    .append(odo.uzmiWhereUslov());
            String upit = sb.toString();
            
            int res = stanje.executeUpdate(upit);
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public int obrisi(IDomenski odo) throws SQLException {
        try {
            Statement stanje = connection.createStatement();
            
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(odo.uzmiNazivTabele())
                    .append(" ")
                    .append(odo.uzmiWhereUslov());
            String upit = sb.toString();
            
            int res = stanje.executeUpdate(upit);
            return res;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
