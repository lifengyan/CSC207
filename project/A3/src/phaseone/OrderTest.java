package phaseone;

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
  public void testOrder() {
    Order order = new Order("Blue","SES",tr);
    assertEquals(order.status,"ordered");
  }

  @Test
  public void testSetStatus() {
    Order order = new Order("Blue","SES",tr);
    order.setStatus("picked");
    assertEquals(order.status, "picked");
   
   
  }

  @Test
  public void testGetOrderCount() {
    Order order = new Order("Blue","SES",tr);
    assertEquals(order.getOrderid(), 1);
    
  }

  @Test
  public void testGetFront() {
    Order order = new Order("Blue","SES",tr);
    assertEquals(order.getFront(),37);
  }

  @Test
  public void testGetBack() {
    Order order = new Order("Blue","SES",tr);
    assertEquals(order.getBack(),38);
  }

}
