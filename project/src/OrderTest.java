package phaseone;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class OrderTest {
  Translate tr = new Translate();
  public static String transtanblePath = new File("src/phaseone/translation.csv").getAbsolutePath();
  
  /**
   * Set up Translation Table.
   * @throws FileNotFoundException while file not found
   */
  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromcsvfile(transtanblePath);
  }

  @Test
  public void testOrder() {
    Order order = new Order("Blue", "SES", tr);
    assertEquals(order.status, "ordered");
  }

  @Test
  public void testSetStatus() {
    Order order = new Order("Blue", "SES", tr);
    order.setStatus("picked");
    assertEquals(order.status, "picked");


  }

  @Test
  public void testGetOrderCount() {
    Order order = new Order("Blue", "SES", tr);
    assertEquals(order.getOrderid(), 1);

  }

  @Test
  public void testGetFront() {
    Order order = new Order("Blue", "SES", tr);
    assertEquals(order.getFront(), 37);
  }

  @Test
  public void testGetBack() {
    Order order = new Order("Blue", "SES", tr);
    assertEquals(order.getBack(), 38);
  }

}
