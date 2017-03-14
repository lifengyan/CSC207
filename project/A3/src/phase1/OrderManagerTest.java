package phase1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class OrderManagerTest {
  Translate tr = new Translate();
  OrderManager om = new OrderManager();
  
  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromCSVFile("/Users/lifengyan/Desktop/CSC207Workspace/group_0411/project/A3/src/phase1/translation.csv");
    om.addOrder("Blue", "SES", tr);
    om.addOrder("Blue", "S", tr);
    om.addOrder("Red", "SES", tr);
    om.addOrder("Red", "S", tr);
  }

  @Test
  public void testOrderManager() {
    assertEquals(0,om.generateNext());
  }

  @Test
  public void testAddOrder() {
    om.addOrder("White", "S", tr);
    om.generatePick();
    om.generatePick();
    assertEquals(0,om.generateNext());  
  }

  @Test
  public void testGeneratePick() {
    fail("Not yet implemented");
  }

  @Test
  public void testGenerateNext() {
    om.generatePick();
    assertEquals(4,om.generateNext());
  }

  @Test
  public void testGetOrders() {
    fail("Not yet implemented");
  }

}
