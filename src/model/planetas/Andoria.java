/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.planetas;

import excepciones.CensusException;

/**
 * 
 * @author usuario
 */
public class Andoria extends Planet {
    /* ningun vulkaniano podra censarse en Andoria
       los nibirian vegetarian solo pueden vivir en Nibiru*/
    
    /**
     * Tests if the Ser is allowed on that planet
     * @param specie String
     * @param attr String
     * @throws CensusException 
     */
    @Override
    public void testNewBeing (String specie, String attr) throws CensusException{
          switch(specie.toLowerCase()){
            case "nibirian":
                if(attr.equalsIgnoreCase("vegetarian")){
                    throw new CensusException(CensusException.SER_UNALLOWED_ON_PLANET);
                }
            case "vulcan":
                    throw new CensusException(CensusException.SER_UNALLOWED_ON_PLANET);
          }    
    }
    
}
