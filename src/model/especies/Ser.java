/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.especies;


import excepciones.CensusException;

/**
 *
 * @author usuario
 */
public abstract class Ser implements Comparable<Ser>{
    
    private String specie;
    private String name;
    private String attr;
    private String planet;
    
    public Ser(String specie, String name, String planet){
        this.specie = specie;
        this.name = name;
        this.planet = planet;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }
    
    
    public void setAttr(String attr) throws CensusException {
        this.attr = attr;
    }
    
    public String getSpecie(){
        return specie;
    }
    
    public String specieToString(){
         return "-"+name+"-"+attr+"-"+planet;
    }
    
    public String simpleToString(){
        return name + "-" + attr;
    }

    @Override
    public String toString() {
        return specie+"-"+name+"-"+attr;
    }

   
    /**
     * Compares by specie and then by name
     * @param o Ser
     * @return int
     */
    @Override
    public int compareTo(Ser o) {
        if(o.getName().equalsIgnoreCase(name)){
            return specie.compareToIgnoreCase(o.getName());
        }
        return name.compareToIgnoreCase(o.getName());
        
    }

   

   
    
}
