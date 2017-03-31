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
  public static String transtanblePath = new File("src/phasetwo/translation.csv").getAbsolutePath();

  /**
   * Setup the translation table.
   * 
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
    assertEquals(om.hasNext(), 4);
  }


  @Test
  public void testGetOrders() {
    om.addOrder("Blue", "SES", tr);
    om.addOrder("Blue", "S", tr);
    om.addOrder("Red", "SES", tr);
    om.addOrder("Red", "S", tr);
    assertEquals(om.getOrders().get(13).getOrderid(), 13);
  }

  @Test
  public void testRepick() {
    om.addOrder("Blue", "SES", tr);
    om.addOrder("Blue", "S", tr);
    om.addOrder("Red", "SES", tr);
    om.addOrder("Red", "S", tr);
    om.repick(17);
    assertEquals(om.getRepick().size(), 4);
    assertEquals(om.generatePick().get(1).getOrderid(), 14);

  }


}
