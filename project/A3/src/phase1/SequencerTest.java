package phase1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SequencerTest {
  Translate tr = new Translate();
  OrderManager om = new OrderManager();
  
  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromCSVFile("/Users/lifengyan/Desktop/CSC207Workspace/group_0411/project/A3/src/phase1/translation.csv");
  }

  @Test
  public void testSequencer() {
    Sequencer sq = new Sequencer("Lory");
    assertEquals(sq.getName(),"Lory");
  }

  @Test
  public void testSequence() {
    Sequencer sq = new Sequencer("Lory");
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
    assertTrue(sq.sequence(4, ot, om).get(0).get(0)==1);
  }

}
