package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SequencerTest {
  Translate tr = new Translate();
  OrderManager om = new OrderManager(); 
  Sequencer sq = new Sequencer("Lory");
  public static String transtanblePath = new File("src/phasetwo"
      + "/translation.csv").getAbsolutePath();
  

  /**
   * Set up translation.
   * @return 
   * @throws FileNotFoundException while file not found
   */
  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromcsvfile(transtanblePath);
    
    
  }

  @Test
  public void testSequencer() {
    assertEquals(sq.getName(), "Lory");
  }
  
  
  @Test
  public void testGetid() {
    sq.ready(4);
    assertEquals(sq.getid(), 4);
  }


  @Test
  public void testScan() {
   assertEquals( sq.scan("1"),0);
  }

  @Test
  public void testCompare() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    sq.ready(8);
    sq.scan("1");
    assertTrue(sq.compare(om));
    sq.scan("2");
    assertTrue(sq.compare(om));    
  }


  @Test
  public void testRepick() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    sq.ready(12);
    sq.scan("9");
    sq.compare(om);
    assertEquals(om.getRepick().size(), 4);  
  }
  
  @Test
  public void testRescan() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    sq.ready(16);
    sq.scan("1");
    sq.rescan(om);
    assertEquals(sq.scan("1"),0);
  }
  
  @Test
  public void testSequencing() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    sq.ready(4);
    ArrayList<String> front = new ArrayList<String>(Arrays.asList("1","3","5","7"));
    ArrayList<String> back = new ArrayList<String>(Arrays.asList("2","4","6","8"));
    ArrayList<ArrayList<String>> pallets = new ArrayList<ArrayList<String>>();
    pallets.add(front);
    pallets.add(back);
    assertEquals(sq.sequencing(om),pallets);
    
  }
  
  
  @Test
  public void testGetName() {
    assertEquals(sq.getName(),"Lory");
  }

}
