/*
 
 */
package model.planetas;

import excepciones.CensusException;

/**
 *
 * @author usuario
 */
public class Nibiru extends Planet{
    
    
     /**
     * Tests if the Ser is allowed on that planet
     * @param specie String
     * @param attr String
     * @throws CensusException 
     */
    @Override
    public void testNewBeing (String specie, String attr) throws CensusException{
         //these planet allows all kind of beigns
    }
}
