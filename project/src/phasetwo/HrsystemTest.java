package phasetwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class HrsystemTest {
	Hrsystem hr = new Hrsystem();
	public static String hrfile = new File("src/phasetwo/hrfile.csv").getAbsolutePath();
	ArrayList<String> sequencinglist = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8"));
	ArrayList<String> front = new ArrayList<String>(Arrays.asList("1","2","3","4"));
	ArrayList<String> back = new ArrayList<String>(Arrays.asList("5","6","7","8"));

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHrsystem() {
		assertTrue(hr.loadingList.isEmpty());
	}

	@Test
	public void testGetSequencer() {
		assertEquals(hr.getSequencer("Amy").getName(),"Amy");
	
	}

	@Test
	public void testGetLoader() {
		assertEquals(hr.getLoader("Sheldon").getName(),"Sheldon");
	}

	@Test
	public void testGetReplenisher() {
	  hr.createEmployee("Unger", "Replenisher");
	  assertEquals(hr.getReplenisher("Unger").getName(),"Unger");
		assertEquals(hr.getReplenisher("Leslie").getName(),"Leslie");
	}

	@Test
	public void testReadFromcsvfile() throws FileNotFoundException {
		hr.readFromcsvfile(hrfile);
		Loader ld = new Loader("alice");
		assertEquals(hr.getLoader("alice").getName(),ld.getName());		
	}

	@Test
	public void testAddtoSequencing() {
		hr.getSequencer("Leslie");
		assertEquals(hr.addtoSequencing(4, sequencinglist),"Leslie recieved the items with pick ID of 4");
		
	}

	@Test
	public void testGetSequencingid() {
	  assertTrue(hr.getSequencingid()==0);	
	}

	@Test
	public void testGetSequencingItem() {
		hr.addtoSequencing(4, sequencinglist);
		assertTrue(hr.getSequencingItem(4).equals(sequencinglist));
	}

	@Test
	public void testAddToloader() {
	  ArrayList<ArrayList<String>> pallets = new ArrayList<ArrayList<String>>();
	  pallets.add(front);
	  pallets.add(back);
	  hr.addToloader(4,pallets);
	  assertEquals(hr.getunloadingList().get(4),pallets);
		
	}

	@Test
	public void testCreateEmployee() {
		hr.createEmployee("Amy", "Sequencer");
		assertEquals(hr.getSequencer("Amy").getName(),"Amy");
	}

	@Test
	public void testGetLoaderId() {
	  assertEquals(hr.getLoaderId(),0);
	  ArrayList<ArrayList<String>> pallets = new ArrayList<ArrayList<String>>();
      pallets.add(front);
      pallets.add(back);
      hr.addToloader(4,pallets);
      assertEquals(hr.getLoaderId(),4);
	}


}
