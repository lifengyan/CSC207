package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SequencerTest {
  Translate tr = new Translate();
  OrderManager om = new OrderManager(); 
  Sequencer sq = new Sequencer("Lory");
  public static String transtanblePath = new File("src/phasetwo"
      + "/translation.csv").getAbsolutePath();
  

  /**
   * Set up translation.
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
    sq.ready(4);
    sq.scan("1");
    assertTrue(sq.compare(om));
    assertEquals(sq.scan("2"),1);    
  }


  @Test
  public void testRepick() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    sq.ready(8);
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
    sq.ready(12);
    sq.scan("1");
    sq.rescan(om);
    assertEquals(sq.scan("1"),0);
  }
  
  
  
  
  @Test
  public void testGetName() {
    assertEquals(sq.getName(),"Lory");
  }

}
