/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kertrats;

import controller.Manager;
import excepciones.CensusException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author usuario
 */
public class KertRats {
    
    private static Manager manager;
    private static String[] data; //the line currently readed
    private static List<String> results = new ArrayList<>(); //exceptions and notifications to save into output.txt in the end
    
    public static void main(String[] args) {
        manager = new Manager();
        //First: look if exists planet txt files, if exists data is loaded into list
        try{
          System.out.println("Searching census .txt files...");
          List<String> seresInFiles = manager.checkCensusState();
          for(String s: seresInFiles){
              data = s.split(" ");
              census();
          }
        }catch( IOException | CensusException e){
          System.out.println(e);
        }
        //here starts the input data from standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Please, paste data and press return:");
        do{
           try{
             line = br.readLine();
             data = line.split(" ");                                                        //borrar luego
             if(data.length == 0){
                 System.out.println("You entered no data");
             }
             else{
                //option testing
                switch(data[0].toLowerCase()){
                    case "c":   //censar un ser
                        census();
                        break;
                    case "b":   //borrar un ser
                        delete();
                        break;
                    case "l":   //listado de seres por planeta
                        list();
                        break;
                    case "m":   //modificar propiedad de un ser
                        modification();
                        break;
                    case "p":   //mostrar seres de una especie
                        listBySpecie();
                        break;
                    case "x":   //Exits and saves on planets name .txt files
                        exitAndSave();
                        break;
                    default:
                        throw new CensusException(CensusException.OPTION_UNALLOWED);  
                       
                }
             }
            }catch (IOException | CensusException  ex) {
                System.out.println(ex.getMessage());
                results.add(ex.getMessage());
            }  
        }while(!data[0].equalsIgnoreCase("x"));   
    }

    

    
    /**
     * Option C: census of a new Ser into a planet
     * @throws excepciones.CensusException
     */
    public static void census() throws CensusException{
            manager.censusSer(data);
            String result = "<OK. New beign censed correctly in the planet>";
            results.add(result);
            System.out.println(result);           
       
    } 
       
    
    
    /**
     * Option B: deletion of a Ser from the list
     * @throws excepciones.CensusException
     */                               
    public static void delete() throws CensusException {
          manager.deleteSer(data);
          String result = "<OK. Ser correctly deleted>";
          results.add(result);
          System.out.println(result);
    }
        
  
                                     //(L) METHODS FOR LIST SERES POPULATIONS
    
    /**
     * Option L: list of all seres by planet
     * @throws CensusException 
     */
    public static void list() throws CensusException {
       
            manager.listSeres(data);
            List<String> seres = manager.listSeres(data);
            if(seres.isEmpty()){
                String result = "<POPULATION BY PLANET (nobody censed yet>";
                results.add(result);
                System.out.println(result);
            }
            else{
                results.add("<POPULATION BY PLANET>");
                System.out.println("<POPULATION BY PLANET>");
                for(String s: seres){
                    results.add(s);
                    System.out.println(s);
                }   
            } 
    }
      
                                    //(M) METHODS FOR MODIFICATIONS
    
    /**
     * Option M: modification of a Ser
     * @throws CensusException 
     */
    public static void modification() throws CensusException{
            manager.modificationSer(data);
            results.add("<OK: Ser correctly modified");
            System.out.println("<OK: Ser correctly modified");    
            
    }
    
    
                                    //(P) METHODS SHOW SERES FROM A SPECIE
    
    /**
     * Option P: Lists all seres from a specie
     * @throws CensusException 
     */
    public static void listBySpecie() throws CensusException {
           List<String> ofSpecie = manager.bySpecie(data);
           results.add("<POPULATION BY RACE>");
           System.out.println("<POPULATION BY RACE>");
           for(String st: ofSpecie){
               results.add(st);
               System.out.println(st);
           }
    }
      
    
    
                                    //(X) EXIT AND SAVE TO TXT METHODS
    
    /**
     * Option X: exists and saves info into .txt files (seres and exceptions)
     * @throws IOException 
     */
    public static void exitAndSave() throws IOException{
          manager.saveData();
          System.out.println("New Seres saved into planet .txt files");
          if(!results.isEmpty()){
            manager.saveExceptions(results);
              System.out.println("Exceptions saved into exceptions.txt");
          }    
    }
      
    
}

