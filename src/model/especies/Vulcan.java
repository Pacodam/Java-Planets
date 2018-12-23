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
public class Vulcan extends Ser{
    
    private int meditation;
    
    
    public Vulcan(String specie, String name, String planet, String special) throws CensusException{
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
    public final void setAttr(String attr) throws CensusException{
        try{
          setMeditation(Integer.parseInt(attr));
          super.setAttr(attr); 
        }catch(NumberFormatException e){
            throw new CensusException(CensusException.INCORRECT_DATA);
        }
    }
    
    public void setMeditation(int meditation) throws CensusException {
        if(meditation < 0 || meditation > 10){
          throw new CensusException(CensusException.INCORRECT_MEDITATION);
         }
         this.meditation = meditation;
   }
    
}
