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
public class Nibirian extends Ser{
    
    private String vegetAttr;
   
    
    public Nibirian (String specie, String name, String planet, String special) throws CensusException{
        super(specie, name, planet);
        setAttr(special);
    }
    
    /**
     * The attribute from the Ser is saved both on the superclass Ser (always as String type) and in the specific subclass
     * because in the specific subclass can be an Int
     * @param attr String
     * @throws CensusException 
     */
     @Override
    public final void setAttr(String attr) throws CensusException {
          setVegetAttr(attr);
          super.setAttr(attr);
          
    }
    
    public void setVegetAttr(String vegetAttr) throws CensusException{
        if(!vegetAttr.equalsIgnoreCase("vegetarian") && !vegetAttr.equalsIgnoreCase("novegetarian")){
           throw new CensusException(CensusException.INCORRECT_DATA);
         }
        else{
            this.vegetAttr = vegetAttr;
        }
      }
    
}
