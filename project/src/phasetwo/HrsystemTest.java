package phasetwo;

import static org.junit.Assert.*;

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
		fail("Not yet implemented");
	}

	@Test
	public void testGetSequencingItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddToloader() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLoaderId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetunloadingList() {
		fail("Not yet implemented");
	}

}
