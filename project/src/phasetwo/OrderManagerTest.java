package phasetwo;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class OrderManagerTest {
  Translate tr = new Translate();
  OrderManager om = new OrderManager();
  public static String transtanblePath = new File("src/phaseone/translation.csv").getAbsolutePath();

  /**
   * Setup the translation table.
   * @throws FileNotFoundException while file not found
   */
  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromcsvfile(transtanblePath);
  }

  @Test
  public void testOrderManager() {
    om.addOrder("Blue", "SES", tr);
    om.addOrder("Blue", "S", tr);
    om.addOrder("Red", "SES", tr);
    om.addOrder("Red", "S", tr);
    assertEquals(om.getOrders().size(), 4);

  }

  @Test
  public void testAddOrder() {
    om.addOrder("Blue", "SES", tr);
    Map<Integer, Order> orders = new HashMap<Integer, Order>();
    assertEquals(om.getOrders(), orders);

  }

  @Test
  public void testGeneratePick() {
    om.addOrder("Blue", "SES", tr);
    om.addOrder("Blue", "S", tr);
    om.addOrder("Red", "SES", tr);
    om.addOrder("Red", "S", tr);
    om.generatePick();
    assertEquals(om.generateNext(), 4);
  }

  @Test
  public void testGenerateNext() {
    om.generatePick();
    assertEquals(0, om.generateNext());
  }

  @Test
  public void testGetOrders() {
    om.addOrder("Blue", "SES", tr);
    om.addOrder("Blue", "S", tr);
    om.addOrder("Red", "SES", tr);
    om.addOrder("Red", "S", tr);
    assertEquals(om.getOrders().get(10).getOrderid(), 10);
  }

}
