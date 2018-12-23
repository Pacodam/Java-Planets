/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;


import excepciones.CensusException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.especies.Ser;
import model.planetas.Planet;

/**
 *
 * @author usuario
 */
public class inputOutputMethods {
    
   private static final String SEPARATOR = File.separator;
   private static final File TXT_FOLDER = new File("census_txt_files");
   private static final File EXCEPTIONS_FOLDER = new File("exceptions_files");
   private static File[] files;
  
  
   /**
    * 
    * @return 
    */
   public static boolean existsFiles(){
       if(TXT_FOLDER.exists()){
           if(TXT_FOLDER.list().length > 0){
               files = TXT_FOLDER.listFiles();
               return true;
           }
       }
       return false;
   }
   
   /**
    * 
    * @return
    * @throws IOException
    * @throws FileNotFoundException
    * @throws CensusException 
    */
   public static List<String> loadBeings () throws  IOException, FileNotFoundException, CensusException {
       List<String> seresInFile = new ArrayList<>();
       for(File file: files){
           String planet = getPlanetFile(file);
           BufferedReader br = new BufferedReader(new FileReader(file));
           String linea;
           while ((linea = br.readLine()) != null) {
             String[] data = linea.split("-");
             if(data.length == 3){
                seresInFile.add("c" + " " + data[0]+ " " +  planet + " " +  data[1] + " " + data[2]);
                String[] newSer = new String[]{"c", data[0], planet, data[1], data[2]};
             } 
           }
           br.close();
           }
        return seresInFile;
        }
   
     /**
      * 
      * @param file
      * @return 
      */
     public static String getPlanetFile(File file){
         String pl = file.getName().split("\\.txt")[0]; 
         String planet = pl.substring(0,1).toUpperCase() + pl.substring(1).toLowerCase();
         return planet;
     } 
     
     /**
      * Saves list Ser objects into respective .txt planet files inside census_files folder. 
      * The method deletes previous existing .txt files. The data is saved when option X input is entered.
      * @param census List Planet
      * @throws IOException 
      */
     public static void censusPersistence(List<Planet> census) throws IOException{
         if(!TXT_FOLDER.exists()){
             TXT_FOLDER.mkdir();
         }
         for(Planet pl: census){
             String namePlanet = pl.getClass().getSimpleName().toLowerCase();
                File f = new File(TXT_FOLDER + SEPARATOR + namePlanet + ".txt");
                if(f.exists()){
                    f.delete();
                }
             if(!pl.getBeings().isEmpty()){
                BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
                for(Ser s: pl.getBeings()){
                  bw.write(s.toString());
                  bw.newLine();  
                }
                bw.close();
             }
         }
    }
     
     /**
      * Saves list of exceptions into exceptions.txt, inside exceptions_files folder
      * @param results List String
      * @throws IOException 
      */
     public static void exceptionsPersistence(List<String> results) throws IOException{
         if(!EXCEPTIONS_FOLDER.exists()){
             EXCEPTIONS_FOLDER.mkdir();
         }
        File f = new File(EXCEPTIONS_FOLDER + SEPARATOR + "exceptions.txt");
        if(f.exists()){
            f.delete();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
        for(String s: results){
            bw.write(s);
            bw.newLine();  
          }
          bw.close();
    }
}
 
    
     
   


