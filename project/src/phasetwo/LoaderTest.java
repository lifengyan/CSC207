package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * @author lifengyan.
 *
 */
public class LoaderTest {
  Translate tr = new Translate();
  OrderManager om = new OrderManager();
  Hrsystem hr = new Hrsystem();
  Loader ld = new Loader("Ian");
  public static String transtanblePath =
      new File("src/phasetwo" + "/translation.csv").getAbsolutePath();
  HashMap<Integer, ArrayList<ArrayList<String>>> loaded;
  HashMap<Integer, ArrayList<ArrayList<String>>> loadinglist;
  ArrayList<ArrayList<String>> ls;


  /**
   * Set up translation.
   * 
   * @throws FileNotFoundException while file not found
   */
  @Before
  public void setUp() throws FileNotFoundException {
    tr.readFromcsvfile(transtanblePath);
    loaded = new HashMap<Integer, ArrayList<ArrayList<String>>>();
    loadinglist = new HashMap<Integer, ArrayList<ArrayList<String>>>();
    ls = new ArrayList<ArrayList<String>>();
    ArrayList<String> front = new ArrayList<>(Arrays.asList("1", "3", "5", "7"));
    ArrayList<String> back = new ArrayList<>(Arrays.asList("2", "4", "6", "8"));
    ls.add(front);
    ls.add(back);
    loadinglist.put(4, ls);
  }

  /**
   * Test method for {@link phasetwo.Loader#Loader(java.lang.String)}.
   */
  @Test
  public void testLoader() {
    assertEquals(ld.getName(), "Ian");
  }

  /**
   * Test method for {@link phasetwo.Loader#ready(int)}.
   */
  @Test
  public void testReady() {
    ld.ready(4);
    assertEquals(ld.getid(), 4);

  }

  /**
   * Test method for scan.
   */
  @Test
  public void testScan() {
    ld.ready(4);
    assertTrue(ld.scan(ls, loadinglist, hr, om));

  }

  /**
   * Test method for {@link phasetwo.Loader#repick(phasetwo.OrderManager)}.
   */
  @Test
  public void testRepick() {
    om.addOrder("White", "S", tr);
    om.addOrder("White", "SE", tr);
    om.addOrder("White", "SES", tr);
    om.addOrder("White", "SEL", tr);
    om.generatePick();
    ArrayList<String> front = new ArrayList<>(Arrays.asList("0", "3", "5", "7"));
    ArrayList<String> back = new ArrayList<>(Arrays.asList("2", "4", "6", "8"));
    ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
    test.add(front);
    test.add(back);
    ld.ready(4);
    assertTrue(!ld.scan(test, loadinglist, hr, om));
    om.generatePick();
    assertEquals(om.hasNext(), 4);
  }

  /**
   * Test method for {@link phasetwo.Loader#load(java.util.HashMap, java.lang.String)}.
   */
  @Test
  public void testLoad() {

  }

  /**
   * Test method for {@link phasetwo.Loader#getName()}.
   */
  @Test
  public void testGetName() {
    assertEquals(ld.getName(), "Ian");
  }

  /**
   * Test method for {@link phasetwo.Loader#getid()}.
   */
  @Test
  public void testGetid() {
    ld.ready(4);
    assertEquals(ld.getid(), 4);
  }

}
