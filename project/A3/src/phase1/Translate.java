package phase1;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Translate {
	//This class translate the translation table into a HashMap
	//Using the pair of SKU numbers as keys
	private ArrayList<ArrayList<String>> table;
	
	public Translate() {
		 this.table = new ArrayList<ArrayList<String>>();
		
	}
	
	public void readFromCSVFile(String filePath) throws FileNotFoundException {
	    System.out.println("reading the translate file");
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            ArrayList<String> info = new ArrayList<>();
            info.add(0, record[0]);
            info.add(1, record[1]);
            info.add(2, record[2]);
            info.add(3, record[3]);
            this.table.add(info);
            
            }      
        scanner.close();
	}
	
	public ArrayList<Integer> translate(String colour, String model) {
	    int i = 0;
	    while (i < this.table.size()) {
	      if (this.table.get(i).get(0).equals(colour) && this.table.get(i).get(1).equals(model)) {
	        ArrayList<Integer> rt = new ArrayList<>();
	        rt.add(Integer.parseInt(this.table.get(i).get(2)));
	        rt.add(Integer.parseInt(this.table.get(i).get(3)));
	        return rt;
	      } else {
	        i++;
	      }

	    }
	    System.out.println("Not in the system");
	    ArrayList<Integer> rt = new ArrayList<>();
	    return rt;
	}

}
