package phase1;

import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Translate {
	//This class translate the translation table into a HashMap
	//Using the pair of SKU numbers as keys
	private HashMap<String, ArrayList<Integer>> table;
	
	public Translate() {
		 this.table = new HashMap<String,ArrayList<Integer>>();
		
	}
	
	public void readFromCSVFile(String filePath) throws FileNotFoundException {
	      
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            String det = record[0] + record[1];
            ArrayList<Integer> sku = new ArrayList<>();
            sku.add(Integer.valueOf(record[2]));
            sku.add(Integer.valueOf(record[3]));
            this.table.put(det, sku);
            
            }      
        scanner.close();
	}
	
	public ArrayList<Integer> translate(String colour, String model) {
		String tr = colour + model;
        return this.table.get(tr);
        		
	}

}
