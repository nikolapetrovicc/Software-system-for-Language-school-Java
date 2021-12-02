/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import java.io.IOException;
import obrada.Server;

/**
 *
 * @author PC
 */
public class Komunikacija {
    private static Komunikacija instanca;
    private Server server;
    
    private Komunikacija(){}
    
    public static Komunikacija getInstanca(){
        if(instanca == null){
            instanca = new Komunikacija();
        }
        return instanca; 
    }
    
    public void Start() throws IOException{
        if(server == null || server.isAlive() == false){
            server = new Server(); 
            server.start();
        }
    }
    
    public void Stop() throws IOException {
        if(server!=null && server.getSoket().isBound()){
            server.Zaustavi();
        }
    }
}
