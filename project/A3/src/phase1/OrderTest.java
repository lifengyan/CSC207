package phase1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;


public class OrderTest {
  Translate tr = new Translate();

  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromCSVFile("/Users/lifengyan/Desktop/CSC207Workspace/group_0411/project/A3/src/phase1/translation.csv");
  }
  
  @Test
  public void testOrder() throws FileNotFoundException {
    Order order = new Order("Blue","SES",tr);
    assertEquals(Order.);
    
  }

  @Test
  public void testSetStatus() throws FileNotFoundException {
    Translate tr = new Translate();
    tr.readFromCSVFile("/Users/lifengyan/Desktop/CSC207Workspace/group_0411/project/A3/src/phase1/translation.csv");
    Order order = new Order("Blue","SES",tr);
    order.setStatus("picked");
    assertEquals("picked",order.status);
  }

  @Test
  public void testGetOrderCount() throws FileNotFoundException {
    Translate tr = new Translate();
    tr.readFromCSVFile("/Users/lifengyan/Desktop/CSC207Workspace/group_0411/project/A3/src/phase1/translation.csv");
    Order order = new Order("Blue","SES",tr);
    assertEquals(1, order.getOrderCount());
  }

  @Test
  public void testGetFront() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetBack() {
    fail("Not yet implemented");
  }

}
