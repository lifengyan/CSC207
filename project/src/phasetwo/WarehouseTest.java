/**
 * 
 */
package phasetwo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author lifengyan
 *
 */
public class WarehouseTest {
  Warehouse wh = new Warehouse();
  

  /**
   * Test method for {@link phasetwo.Warehouse#Warehouse()}.
   */
  @Test
  public void testWarehouse() {
    assertTrue(wh.getLevel(0, 0, 0, 1).report()==30);
  }

  /**
   * Test method for {@link phasetwo.Warehouse#storageInital(java.lang.String)}.
   * @throws FileNotFoundException 
   */
  @Test
  public void testStorageInital() throws FileNotFoundException {
    String warehousePath = new File("src/phasetwo/warehouse.csv").getAbsolutePath();
    wh.storageInital(warehousePath);
    assertTrue(wh.getLevel(1, 1, 1, 1).report()==30);
  }

  /**
   * Test method for {@link phasetwo.Warehouse#removeOneAtLocation(phasetwo.Location)}.
   */
  @Test
  public void testRemoveOneAtLocation() {
    Location ls = new Location("A","1","1","1","1");
    wh.removeOneAtLocation(ls);
    assertTrue(wh.getLevel(0, 1, 1, 1).report()==29);
  }

  /**
   * Test method for {@link phasetwo.Warehouse#getFascia(int, int, int, int)}.
   */
  @Test
  public void testGetFascia() {
    wh.getFascia(0, 1, 1, 1);
    assertTrue(wh.getLevel(0, 1, 1, 1).report()==29);
  }


  /**
   * Test method for {@link phasetwo.Warehouse#getLevel(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)}.
   */
  @Test
  public void testGetLevel() {
    assertTrue(wh.getLevel(0, 1, 1, 1).report()==30);
  }

}
