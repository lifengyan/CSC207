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
  public static String transtanblePath = new File("src/phasetwo/translation.csv").getAbsolutePath();

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
    Sequencer sq = new Sequencer("Lory");
    assertEquals(sq.getName(), "Lory");
  }

  @Test
  public void testSequence() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    ArrayList<Integer> ot = new ArrayList<>();
    ot.add(1);
    ot.add(3);
    ot.add(2);
    ot.add(5);
    ot.add(4);
    ot.add(7);
    ot.add(6);
    ot.add(8);
    Sequencer sq = new Sequencer("Lory");
    assertTrue(sq.sequence(4, ot, om).get(0).get(0) == 1);
  }

}
