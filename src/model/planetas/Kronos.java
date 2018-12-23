/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.planetas;

import excepciones.CensusException;
import java.util.List;
import model.especies.Ser;

/**
 *
 * @author usuario
 */
public class Kronos extends Planet {
    
    
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
          }
          List<Ser> beigns = super.getBeings();
          if(beigns.size() > 29){
               throw new CensusException(CensusException.SER_UNALLOWED_ON_PLANET);
          }
        
            
        }
}
