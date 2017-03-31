package phasetwo;

import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;

public class LevelTest {
  static Level testlevel;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    testlevel = new Level();
  }

  @Test
  public void testAdd() {
    testlevel.add(5);
    assertEquals(5, testlevel.report());
  }

  @Test
  public void testSet() {
    testlevel.set(30);
    assertEquals(30, testlevel.report());
  }

  @Test
  public void testRemoveOne() {
    testlevel.removeOne();
    assertEquals(29, testlevel.report());
  }

  @Test
  public void testReport() {
    assertEquals(30, testlevel.report());
  }

  @Test
  public void testReplenishing() {
    assertEquals(" has 30 left", testlevel.replenishing());
  }
}
