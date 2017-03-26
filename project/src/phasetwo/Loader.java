package phasetwo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Loader implements Worker {
  private  String name;
  public Loader(String name) {
    this.name = name;
  }

  public void load(HashMap<Integer, ArrayList<ArrayList<String>>> loadinglist, String filePath) 
		  throws IOException  {
	  
	  String csvFile = filePath;
	  FileWriter writer = new FileWriter(csvFile);
	  String FrontSku = "";
	  String BackSku = "" ;
	  
              if(loadinglist.keySet().size() == 0){
                  writer.close();
                  return;
              }
		 for (Integer key:  loadinglist.keySet())
		 {
			 for (int i=0; i<2; i++){
				 for (int j=0; j<4; j++){
					 if (i==0){
						 FrontSku = FrontSku + loadinglist.get(key).get(i).get(j) + ",";
					 }else{
						 BackSku = BackSku + loadinglist.get(key).get(i).get(j) + ",";
					 }
				 }
			 }
			 
			 writer.append("pick ID: " + Integer.toString(key) +"," + FrontSku + BackSku);
			 
		 }
		  
	writer.close();
    System.out.println("Loading");
    
    
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public int getid() {
    // TODO Auto-generated method stub
    return 0;
  }



}
