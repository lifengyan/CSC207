package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



/**
 * @author lifengyan.
 */
public class FasciaTest {

  /**
   * Test method for {@link phasetwo.Fascia#Fascia(int)}.
   */
  @Test
  public void testFasciaInt() {
    Fascia fs = new Fascia("1");
    Fascia fl = new Fascia();
    assertEquals(fl.getsku(), null);
    assertEquals(fs.getsku(), "1");
  }

  /**
   * Test method for {@link phasetwo.Fascia#Fascia()}.
   */
  @Test
  public void testFascia() {
    Fascia fs = new Fascia("2");
    Fascia fl = new Fascia();
    assertTrue(!fs.equals(fl));
  }

  /**
   * Test method for {@link phasetwo.Fascia#getsku()}.
   */
  @Test
  public void testGetsku() {
    Fascia fs = new Fascia("2");
    assertEquals(fs.getsku(), "2");
  }

}
