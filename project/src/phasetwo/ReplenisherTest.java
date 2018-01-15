package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author lifengyan.
 *
 */
public class ReplenisherTest {
  Warehouse wh = new Warehouse();
  String[] input = new String[] {"replenisher", "Bob", "replenish", "A", "0", "0", "3"};
  Replenisher rp;


  /**
   * Test method for {@link phasetwo.Replenisher#Replenisher(java.lang.String)}.
   */
  @Test
  public void testReplenisher() {
    Replenisher op = new Replenisher("Bob");
    rp = op;
    assertEquals(rp, op);
  }

  /**
   * Test method for {@link phasetwo.Replenisher#replenish(phasetwo.Warehouse, java.lang.String[])}.
   */
  @Test
  public void testReplenish() {
    rp = new Replenisher("Bob");
    rp.replenish(wh, input);
    assertTrue((wh.getLevel(0, 0, 0, 3).report() == 30));
  }

  /**
   * Test method for {@link phasetwo.Replenisher#getName()}.
   */
  @Test
  public void testGetName() {
    rp = new Replenisher("Bob");
    assertEquals(rp.getName(), "Bob");
  }

  /**
   * Test method for {@link phasetwo.Replenisher#getid()}.
   */
  @Test
  public void testGetid() {
    rp = new Replenisher("Bob");
    assertEquals(rp.getid(), 0);
  }

}
