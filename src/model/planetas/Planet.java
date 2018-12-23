/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.planetas;

import excepciones.CensusException;
import java.util.ArrayList;
import java.util.List;
import model.especies.Ser;

/**
 *
 * @author usuario
 */
public abstract class Planet {
    
    private List<Ser> beings;  //list to store the Seres censed
    private static boolean result;
    
    public Planet(){
        this.beings = new ArrayList<>();
    }
    
    public void addBeing(Ser being){
        beings.add(being);
    }
    
    public List<Ser> getBeings() {
        return beings;
    }
    
    public void setAttr(String attr){
        
    }
    
    /**
     * returns List with seres of the specie param received
     * @param specie String
     * @param planet String
     * @return 
     */
    public List<String> getBySpecie(String specie, String planet){
        List<String> ofSpecie = new ArrayList<>();
        for(Ser ser: beings){
            String simpleC = ser.getClass().getSimpleName();
            if(simpleC.equalsIgnoreCase(specie)){
                ofSpecie.add(specie + ser.specieToString());
            }
        }
        //System.out.println(ofSpecie.size());
        return ofSpecie;
    }
    
     /**
     * Tests if the Ser is allowed on that planet
     * @param specie String
     * @param attr String
     * @throws CensusException 
     */
    public void testNewBeing(String specie, String attr) throws CensusException {
    }
    
    /**
     * Finds a Ser on that planet by name passed. Returns Ser if exists or null elsewhere
     * @param name String
     * @return 
     */
    public Ser findPerName(String name){
        
        for(Ser s: beings){
            if(name.equalsIgnoreCase(s.getName())){
                return s;
            }
        }
        return null;
    }
    
    
}
