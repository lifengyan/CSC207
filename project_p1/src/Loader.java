package phaseone;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Loader extends Worker {

  public Loader(String name) {
    super(name);
  }

  public void load(HashMap<Integer, ArrayList<ArrayList<Integer>>> loadinglist, String filePath) 
		  throws IOException  {
	  
	  String csvFile = filePath;
	  FileWriter writer = new FileWriter(csvFile);
	  String FrontSku = "";
	  String BackSku = "" ;
	  
              if(loadinglist.keySet.size() == 0){
                  writer.close();
                  return;
              }
		 for (Integer key:  loadinglist.keySet())
		 {
			 for (int i=0; i<2; i++){
				 for (int j=0; j<4; j++){
					 if (i==0){
						 FrontSku = FrontSku + Integer.toString(loadinglist.get(key).get(i).get(j)) + ",";
					 }else{
						 BackSku = BackSku + Integer.toString(loadinglist.get(key).get(i).get(j)) + ",";
					 }
				 }
			 }
			 
			 writer.append("pick ID: " + Integer.toString(key) +"," + FrontSku + BackSku);
			 
		 }
		  
	writer.close();
    System.out.println("Loading");
    
    
  }


}
