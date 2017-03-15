package phaseone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Translate {
  // This class translate the translation table into a HashMap
  // Using the pair of SKU numbers as keys
  private ArrayList<ArrayList<String>> table;

  public Translate() {
    this.table = new ArrayList<ArrayList<String>>();

  }

  /**
   * Read the translation table from input file.
   * @param filePath the file to read
   * @throws FileNotFoundException if file is not found
   */
  public void readFromcsvfile(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileInputStream(filePath));
    String[] record;
    while (scanner.hasNextLine()) {
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

  /**
   * Translate the colour and model into front and back SKU numbers.
   * @param colour of minivan   
   * @param model of minivan
   * @return a ArrayList of integer
   */
  public ArrayList<Integer> translate(String colour, String model) {
    int in = 0;
    while (in < this.table.size()) {
      if (this.table.get(in).get(0).equals(colour) && this.table.get(in).get(1).equals(model)) {
        ArrayList<Integer> rt = new ArrayList<>();
        rt.add(Integer.parseInt(this.table.get(in).get(2)));
        rt.add(Integer.parseInt(this.table.get(in).get(3)));
        return rt;
      } else {
        in++;
      }

    }
    System.out.println("Not in the system");
    ArrayList<Integer> rt = new ArrayList<>();
    return rt;
  }

}
