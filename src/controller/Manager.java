/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import excepciones.CensusException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.especies.Andorian;
import model.especies.Human;
import model.especies.Klingon;
import model.especies.Nibirian;
import model.especies.Ser;
import model.especies.Vulcan;
import model.planetas.Andoria;
import model.planetas.Kronos;
import model.planetas.Nibiru;
import model.planetas.Planet;
import model.planetas.Vulcano;
import persistence.inputOutputMethods;

/**
 * Class Manager stores the application login
 * @author alu2017454
 */
public class Manager {
    
    //new Ser object, Ser is abstract, but the instatiation is to one of its subclasses.
    private static Ser newSer;  
    
    //List of planets with its censed beings, loaded at start if exists Vulcan.txt, Klingon.txt or any other planet census files
    private static List<Planet> census; 
    
    //used for find planet position on census, for current censed beign 
    //predefined positions are : 0:andoria, 1:kronos, 2:nibiru, 3:vulcano getClass simplename
    private static int actualPlanet; 
    
    /**
     * When a new Manager is instanced, a Planet list with the four type of planets is created
     */
    public Manager() {
        census = new ArrayList<>();
        census.add(new Andoria());
        census.add(new Kronos());
        census.add(new Nibiru());
        census.add(new Vulcano());
        
    }
    
    /**
     * If exists planet .txt files, this method returns a List String with the data stored there 
     * @return List String
     * @throws IOException
     * @throws FileNotFoundException
     * @throws CensusException 
     */
    public List<String>  checkCensusState() throws IOException, FileNotFoundException, CensusException{
       List<String> population = new ArrayList<>();
       if(inputOutputMethods.existsFiles()){
           return inputOutputMethods.loadBeings();
       }
      return population; 
    }
    
                                    ////(OPTION C) CENSUS METHODS
    
    /**
     * Checks correct length of census option, creates the new Ser and adds into the census List
     * Every possible error in new Ser data is actually controlled and throwed if needed.
     * @param data String[]
     * @throws CensusException
     */
    public void censusSer(String[] data) throws CensusException {
           //verificacion de que se han pasado todos los datos
            if(data.length != 5){
                throw new CensusException(CensusException.INCORRECT_NUM_ARGS);
            }
            else{
              //all input data is unified to obtain a first letter uppercase and the rest to lower case
              String typeSer = data[1].substring(0,1).toUpperCase() + data[1].substring(1).toLowerCase();
              String planet = data[2].substring(0,1).toUpperCase() + data[2].substring(1).toLowerCase();
              String name = data[3].substring(0,1).toUpperCase() + data[3].substring(1).toLowerCase();
              String specialValue = data[4];  
               if(checkPlanet(planet)){
                 if(!existsName(name)){
                     if(createSer(typeSer, name, planet, specialValue)){   
                         //with this line of code, we test if the new Ser is allowed on the planet
                         census.get(actualPlanet).testNewBeing(typeSer, specialValue);
                         /*finally, we add new beign to census list. For now list is on local memory, 
                         until option X, that list will go to persistence on .txt file
                         */
                         census.get(actualPlanet).getBeings().add(newSer);    
                     } 
                }
              }
           }   
    } 
        
    /**
     * Checks the specie of the new Ser. If exists, a new Ser of that specie is created. If not,
 a CensusException is throwed
     * @param typeSer String
     * @param name String
     * @param planet String
     * @param attr String
     * @return boolean
     * @throws CensusException
     */
        public static boolean createSer(String typeSer, String name, String planet, String attr) throws CensusException{
          
              switch(typeSer.toLowerCase()){
                 case "human":
                     newSer = new Human(typeSer, name, planet, attr);
                     break;
                 case "vulcan":
                     newSer = new Vulcan(typeSer, name, planet, attr);
                    break;
                case "andorian":
                    newSer = new Andorian(typeSer,name, planet, attr);
                    break;
                case "nibirian":
                    newSer = new Nibirian(typeSer,name, planet, attr);
                    break;
                case "klingon":
                    newSer = new Klingon(typeSer, name, planet, attr);
                    break;
                default:
                   throw new CensusException(CensusException.INCORRECT_SPECIE);
              }
              return true;
    }
        
    /**
     * Checks if the planet is correct. If not, an exception is throwed
     * @param planet String
     * @return boolean
     * @throws excepciones.CensusException 
     */
    public static boolean checkPlanet(String planet) throws CensusException{
      
            switch(planet.toLowerCase()){
                case "vulcano":
                    actualPlanet = 3;
                    return true;
                case "andoria":
                    actualPlanet = 0;
                    return true;
                case "nibiru":
                    actualPlanet = 2;
                    return true;
                case "kronos":
                    actualPlanet = 1;
                    return true;
                default:
                  throw new CensusException(CensusException.INCORRECT_PLANET); 
            }
    }
   
    /**
     * Checks if exists any Ser with that name. If exists, an exception is throwed
     * @param name String
     * @return boolean
     * @throws excepciones.CensusException 
     */
     public static boolean existsName(String name) throws CensusException{
    
         for(Planet pl: census){
            for(Ser s: pl.getBeings()){
               if(name.equalsIgnoreCase(s.getName())){
                  throw new CensusException(CensusException.SER_EXISTS);
               }
            }
         }
        return false; 
    }
        
                                //(B) METHODS FOR DELETE SER
     
    /**
     * Deletes a Ser censed
     * @param data String[] 
     * @throws excepciones.CensusException 
     */
    public  void deleteSer(String[] data) throws CensusException {
        if(data.length != 2){
            throw new CensusException(CensusException.INCORRECT_NUM_ARGS);
        }
        if(!checkIfExistsName(data[1])){
           throw new CensusException(CensusException.SER_NO_EXISTS);
        }
        else{
            for(Planet pl: census){
                for(int i = 0; i < pl.getBeings().size(); i++){
                    if(pl.getBeings().get(i).getName().equalsIgnoreCase(data[1])){
                        pl.getBeings().remove(i);
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param name String
     * @return boolean
     */
    public static boolean checkIfExistsName(String name){
         for(Planet pl: census){
            for(Ser s: pl.getBeings()){
               if(name.equalsIgnoreCase(s.getName())){
                   return true;
               }
            }
         }
       return false; 
     }
    
    
                            //(L) METHODS FOR LIST SERES POPULATIONS
    
    /**
     * List all censed Seres by planet
     * @param data String[]
     * @return List String
     * @throws excepciones.CensusException
     */
    public List<String> listSeres(String[] data)throws CensusException {
        // TODO The results are not ordered as required. Should be revised.
        if(data.length != 1){
            throw new CensusException(CensusException.INCORRECT_NUM_ARGS);
        }
        List<String> population = new ArrayList<>();
        for(Planet pl: census){
           if(!pl.getBeings().isEmpty()){
              population.add("<" + pl.getClass().getSimpleName() + ">");
              List<Ser> seres = pl.getBeings();
              Collections.sort(seres);
              for(Ser s: seres){
                 population.add(s.toString());
              }
           }
        } 
        return population;
    }
    
    /**
     * Verifies if exists some Ser censed in any of the four planets
     * @return boolean
     */
    public static boolean isAnyone(){
        for(Planet pl: census){
            if(!pl.getBeings().isEmpty()){
                return true;
            }
        }
        return false;
    }
    
                       //(M) METHODS FOR MODIFICATIONS
    
    /**
     * Modification of a previous censed Ser
     * @param data String[] 
     * @throws CensusException
     */
    public void modificationSer(String[] data) throws CensusException {
            if(data.length != 3){
                throw new CensusException(CensusException.INCORRECT_NUM_ARGS);
            }
            else{
               if(!nameSearch(data)){
                 throw new CensusException(CensusException.SER_NO_EXISTS);
               }  
            }
    }
        
    /**
     * Searches Ser by name and if exists changes attr. Return true if ok.
     * @param data String[]
     * @return boolean
     * @throws CensusException
     */
    public static boolean nameSearch(String[] data) throws CensusException {
        for(Planet pl: census){
           Ser s =  pl.findPerName(data[1]);
           if(s != null){
               s.setAttr(data[2]);
               return true;
           }
        }
        return false;
        
    }
    
    
                 //(P) METHODS SHOW SERES FROM A SPECIE
    
    /**
     * Checks if the specie to list exists
     * @param data String[]
     * @return List String
     * @throws CensusException 
     */
    public List<String> bySpecie(String[] data) throws CensusException{
      
       if(data.length != 2){
          throw new CensusException(CensusException.INCORRECT_NUM_ARGS);
       }
        switch(data[1].toLowerCase()){
            case "human":
                break;
            case"vulcan":
                break;
            case "andorian":
                break;
            case "nibirian":
                break;
            case "klingon":
               break;
            default:
                throw new CensusException(CensusException.INCORRECT_SPECIE);      
        }
         return specieList(data[1]);
    }
    
    
    /**
     * Is specie exists, this method returns a List String with all Seres, ordered by Specie and name
     * @param sp String
     * @return List String
     */
    public static List<String> specieList(String sp) {
         String specie = sp.substring(0,1).toUpperCase() + sp.substring(1).toLowerCase();
         List<String> ofSpecie = new ArrayList<>();
         for(Planet pl: census){
            List<String> speciePlanet = pl.getBySpecie(specie, pl.getClass().getSimpleName());
            for(String st: speciePlanet){
                ofSpecie.add(st);
            }     
        }
        return ofSpecie;
    }
    
                                 //(X) EXIT AND SAVE TO TXT METHODS
    
    /**
     * Saves de data stored on the list into respective [nameOfPlanet].txt file
     * @throws IOException 
     */
    public void saveData() throws IOException{
          inputOutputMethods.censusPersistence(census);
    }
    
    public void saveExceptions(List<String> results) throws IOException{
        inputOutputMethods.exceptionsPersistence(results);
    }

    
    
}
