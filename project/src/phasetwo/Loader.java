package phasetwo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Loader implements Worker {
  private  String name;
  private ArrayList<Integer> repickid;
  
  public Loader(String name) {
    this.name = name;
  }
  
  public ArrayList<Integer> scan(HashMap<Integer, ArrayList<ArrayList<String>>> loadinglist,
      OrderManager om) {
    ArrayList<Integer> repickid = new ArrayList<Integer>();
    for (Integer key : loadinglist.keySet()) {
      int in = 3;
      while (in > -1) {
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < 4; j++) {
            if (i == 0) {
              if (!loadinglist.get(key).get(i).get(j)
                  .equals(om.getOrders().get(key - in).getFront())) {
                repickid.add(key);
              }
            } else {
              if (!loadinglist.get(key).get(i).get(j)
                  .equals(om.getOrders().get(key - in).getBack())) {
                repickid.add(key);
              }

            }

          }
        }
      }

    }
    return repickid;
  }

    
    
  public void add(Integer in) {
    if (! this.repickid.contains(in)) {
      this.repickid.add(in);
    }  
  }
  
  public void repick(OrderManager om) {
    for (Integer i: this.repickid) {
      om.repick(i);     
    }
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
