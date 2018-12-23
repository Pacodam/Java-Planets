/*
 no se admiten andorian
no se admiten klingon

 */
package model.planetas;

import excepciones.CensusException;

/**
 *
 * @author usuario
 */
public class Vulcano extends Planet {
    
    
     /**
     * Tests if the Ser is allowed on that planet
     * @param specie String
     * @param attr String
     * @throws CensusException 
     */
    @Override
    public void testNewBeing (String specie, String attr) throws CensusException{
          switch(specie.toLowerCase()){
            case "klingon":
                throw new CensusException(CensusException.SER_UNALLOWED_ON_PLANET);
            case "andorian":
               throw new CensusException(CensusException.SER_UNALLOWED_ON_PLANET);
             case "nibirian":
                if(attr.equalsIgnoreCase("vegetarian")){ 
                   throw new CensusException(CensusException.SER_UNALLOWED_ON_PLANET);
                }
               
          }    
    }
}
